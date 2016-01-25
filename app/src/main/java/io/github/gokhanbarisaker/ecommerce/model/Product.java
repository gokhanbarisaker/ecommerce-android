package io.github.gokhanbarisaker.ecommerce.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product implements Parcelable {

    // == Variables ================================================================================

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
    @SerializedName("sku")
    @Expose
    private String sku;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("price")
    @Expose
    private Price price;
    @SerializedName("brand")
    @Expose
    private String brand;
    @SerializedName("image")
    @Expose
    private String image;


    // == Constructors & factory methods ===========================================================
    @SerializedName("published_at")
    @Expose
    private String publishedAt;

    public Product() {

    }


    // == Accessors ================================================================================

    protected Product(Parcel in) {
        sku = in.readString();
        name = in.readString();
        price = (Price) in.readValue(Price.class.getClassLoader());
        brand = in.readString();
        image = in.readString();
        publishedAt = in.readString();
    }

    public static Product random() {
        Product product = new Product();

        product.brand = "Foo";
        product.name = "Bar";
        product.sku = "Baz";

        product.price = Price.random();
        product.image = "https://drscdn.500px.org/photo/51109566/m%3D2048/4bdbaab2901b03b7fe54d3f1f305444b";

        return product;
    }

    /**
     * @return The sku
     */
    public String getSku() {
        return sku;
    }

    /**
     * @param sku The sku
     */
    public void setSku(String sku) {
        this.sku = sku;
    }

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The price
     */
    public Price getPrice() {
        return price;
    }

    /**
     * @param price The price
     */
    public void setPrice(Price price) {
        this.price = price;
    }

    /**
     * @return The brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * @param brand The brand
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * @return The image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image The image
     */
    public void setImage(String image) {
        this.image = image;
    }


    // == Parcel stuff =============================================================================

    /**
     * @return The publishedAt
     */
    public String getPublishedAt() {
        return publishedAt;
    }

    /**
     * @param publishedAt The published_at
     */
    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sku);
        dest.writeString(name);
        dest.writeValue(price);
        dest.writeString(brand);
        dest.writeString(image);
        dest.writeString(publishedAt);
    }
}
