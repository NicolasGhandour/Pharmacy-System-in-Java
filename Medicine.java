/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nicolas_ghandour;

/**
 * This class simulates a medicine that has a name, composition, dose, price, 
 * and quantity
 * @version 1.1 April 2023
 * @author Nicolas Ghandour
 */
public class Medicine {
    private String name; /*represents the name of the medicine.*/
    private String composition; /*represents the composition of the medicine*/
    private int dose; /*represents the dose of the medicine in mg*/
    private double price ;  /* represents the price of the medicine.*/
    private int quantity;  /*represents the quantity of the medicine.*/

                 /* Contructors */ 
        
    /*With-argument constructor that use the setters to set the data fields.*/
    public Medicine(String name,String composition,int dose,double price,
                    int quantity) {
        setName(name);
        setComposition(composition);
        setDose(dose);
        setPrice(price);
        setQuantity(quantity);
    }

    /**
    * With-argument constructor that takes as parameter the name, composition  
    * and dose of the medicine and sets the other data field to their default  
    * values by calling the first constructor
    * @param name
    * @param composition
    * @param dose
    */
    public Medicine(String name,String composition,int dose) {
        this(name,composition, dose,10,0);
    }

    /*The mutators*/
    
    /* sets the name of the medicine with all letters set to lower cases. */
    public void setName(String name) {
        this.name = name.toLowerCase();
    }

    /*sets the composition of the medicine with all letters set to lower cases*/ 
    public void setComposition(String composition) {
        this.composition = composition.toLowerCase();
    }

    /* sets the dose of the medicine and assigns the default value 1000 if the  
     * arg is negative or equal to zero. 
     */
    public void setDose(int dose) {
        this.dose = (dose>0) ? dose :1000;
    }

    /* sets the price of the medicine and assigns the default value 1000 if the  
     * arg is negative or equal to zero. 
     */
    public void setPrice(double price) {
        this.price = (price>0) ? price :10;
    }

 
    /* sets the quantity of the medicine and assigns the default value 0 if the  
     * arg is negative or equal to zero. 
     */
    public void setQuantity(int quantity) {
        this.quantity = (quantity>0) ? quantity : 0;
    }

       /*The accessors*/

    public String getName() {
        return name;
    }

    public String getComposition() {
        return composition;
    }

    public int getDose() {
        return dose;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
         return quantity;
    }

    /**
    * toString method used to display information about a pharmacy
    *@return a String having the information.
    */
    public String toString() {
        return  "\t" + "name: " + name + "\n" + "\t" + "composition: " 
                + composition + "\n" + "\t" + "dose: " + dose + "mg" + "\n" 
                + "\t" + "price: " + price +  "\n" + "\t" + "quantity: "
                + quantity + "\n" ; 
    }

    /**
    * equals methods to test whether 2 medicines are equal or not.
    * @param medicine
    *@return  if they both have the same name and the same dose.
    */
    public boolean equals(Medicine medicine) {
        return (name.equals(medicine.name) && dose == medicine.dose);
    }
}
