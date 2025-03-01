/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nicolas_ghandour;

/**
 *This class simulates a medicine of type prescription.
 * This class inherits the Medicine class, since a prescription medicine is 
 * a medicine
 * @version 1.1 April 2023
 * @author Nicolas Ghandour
 */
public class Prescription extends Medicine {
    private String  doctorSpecialization;//represents the specialization of the 
    //doctor allowed prescribing this medicine

                            /*Constructors*/
    
    /**
     * With-argument constructor that takes as parameter the name, composition,  
     * dose and the doctor specialization of the medicine and sets the other  
     * data field to their default values by calling the second constructor.
     * @param name
     * @param composition
     * @param dose
     * @param doctorSpecialization
     */
    public Prescription(String name,String composition,int dose,
                        String doctorSpecialization) {
        this(name,composition,dose,10,0,doctorSpecialization); 
        //calling the second constructor since the second takes more parameters
    }

    /**
     * With-argument constructor that calls the setter of the doctor 
     * specialization and the constructor of the super class (Medicine) to 
     * initialize the rest of the data fields.
     *@param name
     *@param composition
     *@param dose
     *@param quantity
     *@param price
     *@param doctorSpecialization
     */
    public Prescription(String name,String composition,int dose,double price,
                        int quantity,String doctorSpecialization){
        super(name, composition, dose, price,quantity);//calling the constructor  
        // of the super class
        setDoctorSpecialization(doctorSpecialization);
    }

    public String getDoctorSpecialization() {
        return doctorSpecialization;
    }

    public void setDoctorSpecialization(String doctorSpecialization) {
        this.doctorSpecialization = doctorSpecialization;
    }

    /*displays information about a medicine of type prescription */
    /**
     *@return a String containing the information of the Prescription medicine
     */
    public String toString(){
        return super.toString() + // invoking the toString() in the Medicine  
           "\t" + "doctor specialization: " + doctorSpecialization; // class  
                         // adding the doctor specializition info to the String
    }
    
}
