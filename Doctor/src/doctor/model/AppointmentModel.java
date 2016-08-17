/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doctor.model;

import java.util.Date;

/**
 *
 * @author Keilah
 */
public class AppointmentModel extends BaseModel {
 
    private TeamModel doctor;
    private Date dateOfAppintment;
    private PatientModel patient;
    private String notes;
    private String prescription;

    public TeamModel getDoctor() {
        return doctor;
    }

    public void setDoctor(TeamModel doctor) {
        this.doctor = doctor;
    }

    public Date getDateOfAppintment() {
        return dateOfAppintment;
    }

    public void setDateOfAppintment(Date dateOfAppintment) {
        this.dateOfAppintment = dateOfAppintment;
    }

    public PatientModel getPatient() {
        return patient;
    }

    public void setPatient(PatientModel patient) {
        this.patient = patient;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }
    
    
    
    
    
}
