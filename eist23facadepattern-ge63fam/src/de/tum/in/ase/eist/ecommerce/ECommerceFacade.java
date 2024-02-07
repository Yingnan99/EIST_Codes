package de.tum.in.ase.eist.ecommerce;

public class ECommerceFacade {

    private AdvertisementController advertisementController;

    private OrderController orderController;

    private ShippingController shippingController;

    public ECommerceFacade() {
        this.advertisementController = new AdvertisementController();
        this.orderController = new OrderController();
        this.shippingController = new ShippingController();
    }

    public void processOrder(Order order) {
        orderController.processOrder(order);
    }

    public void processOrder(Order order, String phoneNumber) {
        orderController.processOrder(order,phoneNumber);
    }

    public Order retrieveLatestOrder(int id) {
        return orderController.retrieveLatestOrder(id);
    }

    public void playAdvertisement(int ageRestriction) {
        advertisementController.playAdvertisement(ageRestriction);
    }

    public void shipOrder(Order order, String address) {
        Shipping shipping = shippingController.createShipping(address);
        order.setShipping(shipping);
        shippingController.shipOrder(order);
    }


    public AdvertisementController getAdvertisementController() {
        return advertisementController;
    }

    public void setAdvertisementController(AdvertisementController advertisementController) {
        this.advertisementController = advertisementController;
    }

    public OrderController getOrderController() {
        return orderController;
    }

    public void setOrderController(OrderController orderController) {
        this.orderController = orderController;
    }

    public ShippingController getShippingController() {
        return shippingController;
    }

    public void setShippingController(ShippingController shippingController) {
        this.shippingController = shippingController;
    }
}
