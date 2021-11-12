package it.iagica.shoppingsite.Model;

public class Item {
    private Integer idItem;
    private String code;
    private String name;
    private String description;
    private Double price;
    private String img;
    
    
    public Item() {
    }
    
    

    public Item(Integer idItem, String code, String name, String description, Double price ,String img ) {
		super();
		this.idItem = idItem;
		this.code = code;
		this.name = name;
		this.description = description;
		this.price =  price;
		this.img = img;
	
	}



	public String getImg() {
		return img;
	}



	public void setImg(String img) {
		this.img = img;
	}



	public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "idItem=" + idItem +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
    	
}
