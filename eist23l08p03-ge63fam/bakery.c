#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct bakery {
  char *name;
  char *pastries;
};

struct bakery *bakeries;

int main(int argc, char **argv) {
  /*
   * Do not change anything in the switch
   */
  int count = 0;
  switch (argc) {
  case 2:
    if (strcmp(argv[1], "-h") == 0) {
      printf(
          "This program accepts the following arguments:\n-h\n-c <count> "
          "<bakery names separated by space> <pastries separated by space>\n");
    }
    return 0;
  case 5:
    if (strcmp(argv[1], "-c") == 0 && sscanf(argv[2], "%i", &count) == 1) {
      printf("%i Bakeries are created\n", count);
    }
    break;
  default:
    return EXIT_FAILURE;
  }

  char *s;

  // Read the given names argument

  // char **names 是一个指向字符指针数组的指针。这意味着 names 是一个数组，它的每个元素都是一个 char *，即可以指向一个字符串的指针。因此，names[i] 是一个指向第 i 个字符串的指针
  char **names = malloc(count * sizeof(char *)); // String[] names = new String[count];


  //count 是我们期望放入的名字的数量
  //检查count是否为0
  if(count == 0) {
    return EXIT_FAILURE;
  }
  //需要检查内存分配问题，因为这里的names是数组，如果内存不足则返回NULL
  if(names == NULL){
    return EXIT_FAILURE;
  }

  s = strtok(argv[3], " ");
  int k = 0; //k 是用来追踪我们在 names 数组中放入了多少个名字的变量

  //在 while 循环中，每当我们成功地从输入中解析出一个新的名字并将其放入 names 数组，我们就会增加 k 的值。
  while (s != NULL) {
    if (k >= count) {  //这里需要将k > count 改为 k >= count。因为在原代码中，如果 k 比 count 大，说明给出的名字数量多于预期。但实际上，当 k 等于 count 时，我们的 names 数组已经没有额外的空间来存放更多的名字了，因此，应该在 k 等于 count 时就打印错误消息并退出。
      printf("Too many names have been given\n");
      free(names);
      return EXIT_FAILURE;
    }

    names[k] = s;
    s = strtok(NULL, " ");
    k++;
  }
  if (k != count) {
    printf("Not enough names have been given\n");
    free(names);
    return EXIT_FAILURE;
  }

  // Read the given pastry argument
  char **pastries = malloc(count * sizeof(char *));

  //需要检查内存分配问题，因为这里pastries是数组，如果内存不足则返回NULL
  if (pastries == NULL) {
    return EXIT_FAILURE;
  }

  s = strtok(argv[4], " ");
  k = 0;
  while (s != NULL) {
    if (k >= count) {
      printf("Too many pastries have been given\n");
      free(names);
      free(pastries);   //释放之前分配的内存，以避免内存泄漏。
      return EXIT_FAILURE;
    }
    pastries[k] = s;
    s = strtok(NULL, " ");
    k++;
  }
  if (k != count) {
    printf("Not enough pastries have been given\n");
    free(names);
    free(pastries); //释放之前分配的内存，以避免内存泄漏。
    return EXIT_FAILURE;
  }

//  free(names);
//  free(pastries);

  bakeries = malloc(count * sizeof(struct bakery)); //Bakery[] bakeries = new Bakery[count];

  //需要检查内存分配问题，因为这里bakeries是数组，如果内存不足则返回NULL
  if (bakeries == NULL) {
     return EXIT_FAILURE;
  }

//  struct bakery *tempBakery;

  // Run count times to initialize the structs
  for (int i = 0; i < count; i++) {
      bakeries[i].name = names[i];
      bakeries[i].pastries = pastries[i];
      printf("Creating Bakery %s with pastries %s\n", bakeries[i].name,
             bakeries[i].pastries);
  }
  free(names);
  free(pastries);


  free(bakeries);
  return EXIT_SUCCESS;
}
