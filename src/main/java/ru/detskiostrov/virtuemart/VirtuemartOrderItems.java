/*
 *  Class stories all items has been saled during given period
 *   Author: Grigorii Andreev
 *   Date: 04 April 2021
 */

package ru.detskiostrov.virtuemart;

public class VirtuemartOrderItems {
    int virtuemartOrderItemId;
    int virtuemartOrderId;
    int virtuemartVendorId;
    int virtuemartProductId;
    String orderItemSku;
    String orderItemName;
    int productQuantity;
    int productItemPrice;
    int productPriceWithoutTax;
    int productTax;
    int productBasePriceWithTax;
    int productDiscountedPriceWithoutTax;
    int productFinalPrice;
    int productSubtotalDiscount;
    int productSubtotalWithTax;
    int orderItemCurrency;
    String orderStatus;
    int productAttribute;
    String deliveryDate;
    String paid;
    String oiHash;
    String createdOn;
    int createdBy;
    int modifiedOn;
    int modifiedBy;
    int lockedOn;
    int lockedBy;
    int virtuemartManufacturerId;
    String orderNumber;

    public int getVirtuemartManufacturerId() {
        return virtuemartManufacturerId;
    }

    public void setVirtuemartManufacturerId(int virtuemartManufacturerId) {
        this.virtuemartManufacturerId = virtuemartManufacturerId;
    }



    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }



    public int getVirtuemartOrderItemId() {
        return virtuemartOrderItemId;
    }

    public void setVirtuemartOrderItemId(int virtuemartOrderItemId) {
        this.virtuemartOrderItemId = virtuemartOrderItemId;
    }

    public int getVirtuemartOrderId() {
        return virtuemartOrderId;
    }

    public void setVirtuemartOrderId(int virtuemartOrderId) {
        this.virtuemartOrderId = virtuemartOrderId;
    }

    public int getVirtuemartVendorId() {
        return virtuemartVendorId;
    }

    public void setVirtuemartVendorId(int virtuemartVendorId) {
        this.virtuemartVendorId = virtuemartVendorId;
    }

    public int getVirtuemartProductId() {
        return virtuemartProductId;
    }

    public void setVirtuemartProductId(int virtuemartProductId) {
        this.virtuemartProductId = virtuemartProductId;
    }

    public String getOrderItemSku() {
        return orderItemSku;
    }

    public void setOrderItemSku(String orderItemSku) {
        this.orderItemSku = orderItemSku;
    }

    public String getOrderItemName() {
        return orderItemName;
    }

    public void setOrderItemName(String orderItemName) {
        this.orderItemName = orderItemName;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public int getProductItemPrice() {
        return productItemPrice;
    }

    public void setProductItemPrice(int productItemPrice) {
        this.productItemPrice = productItemPrice;
    }

    public int getProductPriceWithoutTax() {
        return productPriceWithoutTax;
    }

    public void setProductPriceWithoutTax(int productPriceWithoutTax) {
        this.productPriceWithoutTax = productPriceWithoutTax;
    }

    public int getProductTax() {
        return productTax;
    }

    public void setProductTax(int productTax) {
        this.productTax = productTax;
    }

    public int getProductBasePriceWithTax() {
        return productBasePriceWithTax;
    }

    public void setProductBasePriceWithTax(int productBasePriceWithTax) {
        this.productBasePriceWithTax = productBasePriceWithTax;
    }

    public int getProductDiscountedPriceWithoutTax() {
        return productDiscountedPriceWithoutTax;
    }

    public void setProductDiscountedPriceWithoutTax(int productDiscountedPriceWithoutTax) {
        this.productDiscountedPriceWithoutTax = productDiscountedPriceWithoutTax;
    }

    public int getProductFinalPrice() {
        return productFinalPrice;
    }

    public void setProductFinalPrice(int productFinalPrice) {
        this.productFinalPrice = productFinalPrice;
    }

    public int getProductSubtotalDiscount() {
        return productSubtotalDiscount;
    }

    public void setProductSubtotalDiscount(int productSubtotalDiscount) {
        this.productSubtotalDiscount = productSubtotalDiscount;
    }

    public int getProductSubtotalWithTax() {
        return productSubtotalWithTax;
    }

    public void setProductSubtotalWithTax(int productSubtotalWithTax) {
        this.productSubtotalWithTax = productSubtotalWithTax;
    }

    public int getOrderItemCurrency() {
        return orderItemCurrency;
    }

    public void setOrderItemCurrency(int orderItemCurrency) {
        this.orderItemCurrency = orderItemCurrency;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getProductAttribute() {
        return productAttribute;
    }

    public void setProductAttribute(int productAttribute) {
        this.productAttribute = productAttribute;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public String getOiHash() {
        return oiHash;
    }

    public void setOiHash(String oiHash) {
        this.oiHash = oiHash;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public int getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(int modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public int getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(int modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public int getLockedOn() {
        return lockedOn;
    }

    public void setLockedOn(int lockedOn) {
        this.lockedOn = lockedOn;
    }

    public int getLockedBy() {
        return lockedBy;
    }

    public void setLockedBy(int lockedBy) {
        this.lockedBy = lockedBy;
    }

    public VirtuemartOrderItems() {
    }
}
