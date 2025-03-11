package br.edu.ifpb.exemplosjpa.amqp;

public enum QueuesAmqp {

    EMAIL_QUEUE("email-queue"),
    PURCHASE_QUEUE("purchase-queue"),

    EXCHANGE("exchange"),

    EMAIL_QUEUE_ROUTING_KEY("email"),
    PURCHASE_QUEUE_ROUTING_KEY("purchase");

    private final String value;

    QueuesAmqp(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
