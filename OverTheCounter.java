/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package nicolas_ghandour;

/**
  * This class simulates a medicine of type over the counter.
  * This class inherits the Medicine class, since an over the counter medicine  
  * is a medicine
  * @version 1.1 April 2023
  * @author Nicolas Ghandour
  */
public class OverTheCounter extends Medicine {
    private int minAge;//represents the minimum age for which this                  
                       //medicine is allowed to be given

                           /*Constructors*/
    
    /**
     * With-argument constructor that takes as parameter the name, composition,  
     * dose and the minimum age allowed for the medicine to be given and sets 
     * the other data field to their default values by calling the second 
     * constructor.
     * @param name
     * @param composition
     * @param dose
     * @param minAge
     */
    public OverTheCounter(String name,String composition,int dose,
                          int minAge){
        this(name,composition,dose,10,0,minAge);
    }

    /**
     * With-argument constructor that calls the setter of the minimum age
     * and the constructor of the super class (Medicine) to initialize the rest 
     * of the data fields.
     * @param name
     * @param composition
     * @param dose
     * @param quantity
     * @param price
     * @param minAge
     */
    public OverTheCounter(String name,String composition,int dose,double price,
                         int quantity,int minAge){
        super( name, composition, dose, price, quantity);
        setMinAge(minAge);
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {   
        this.minAge = (minAge>0) ? minAge : 18;
    }

    public String toString() {
        return super.toString()  
               + "\t" + "minimum Age: " + minAge;
    }
}
