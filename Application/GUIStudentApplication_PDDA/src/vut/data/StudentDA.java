/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vut.data;

import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author user 
 */
public class StudentDA
{
    private static ArrayList<StudentClass>arStud;
    private static final String studfile="studs.dat"; 
    
public static void initialize()throws DataStorageException{
    try{
       FileInputStream fis=new FileInputStream(studfile);
       ObjectInputStream objRead=new ObjectInputStream(fis);
       arStud=(ArrayList<StudentClass>)objRead.readObject();
       objRead.close();
   }catch(FileNotFoundException ex){
       JOptionPane.showMessageDialog(null,"No data in the file "+ex.getMessage());
       
       arStud=new ArrayList<>();
   }catch(IOException ex){
   throw new DataStorageException("No data to read "+ex.getMessage());
   }catch(ClassNotFoundException ex){
     JOptionPane.showMessageDialog(null, ex.getMessage());
    }
}//initialize

public static void terminate()throws DataStorageException{
  try{
    FileOutputStream fos=new FileOutputStream(studfile);
    ObjectOutputStream objWrite=new ObjectOutputStream(fos);
   
    objWrite.writeObject(arStud);
  
    objWrite.close();
       
    }catch(IOException ex){
        throw new DataStorageException("Cannot write "+ex.getMessage());
    }
    }//terminate
   
  //method to add new record to an arraylist, and check for duplicates
  public static void addNew(StudentClass stud)throws DuplicateException{
    boolean duplicate=false;
        for (int i = 0; i < arStud.size()&&!duplicate; i++) {
            if(arStud.get(i).getStdno().equals(stud.getStdno())){
            duplicate=true;
            }
            
        }
        if(duplicate){
        throw new DuplicateException("This Stud record already exist");
        }else{
        arStud.add(stud);
        }
    }//add

 //method to find and return a record of a specific student from array
    static StudentClass findStud(String stdno)throws NotFoundException{
    boolean found=false;
    StudentClass stud=null;
    for (int i = 0; i < arStud.size()&&!found; i++) {
        stud=arStud.get(i);
            if(stud.getStdno().equals(stdno)){
            found=true;
            }
            
        }
    if(found){
    return stud;
    }else{
    throw new NotFoundException(stdno+" Not found");
    }
    }//find

    //method to update a field(s) for a specific student from arraylist
 public static void updateMarks(StudentClass stud, int newmarks)throws NotFoundException{
    boolean found=false;
    for (int i = 0; i < arStud.size()&&!found; i++) {
            if(arStud.get(i).getStdno().equals(stud.getStdno())){
            found=true;
            //updates a field of a specific stud
            arStud.get(i).setMarks(newmarks);
            }
            
        }
    if(!found){
    throw  new NotFoundException(stud.getStdno()+" Not found for updating");
    }
    }//update

    //method to delete a record of a specific student
 public static void deleteStud(StudentClass stud) throws NotFoundException{
    boolean found=false;
    
     for (int i = 0; i < arStud.size()&&!found; i++) {
            if(arStud.get(i).getStdno().equals(stud.getStdno())){
            found=true;
            arStud.remove(i);//removes  record a specific stud from arraylist
            }
            
        }
    if(!found){
    throw  new NotFoundException(stud.getStdno()+" Not found for deleting");
    }
    }//del

    //method that allow the retrieval of all members of a class, for specific input 
  public static ArrayList<StudentClass>getAll(String scode){
    ArrayList arD=new ArrayList<>();
    for(StudentClass stud:arStud){
        if(stud.getSubjcode().equals(scode)){
        arD.add(stud);
        }
    }
    return arD;
    }//retrieve for specific input

  //method that allow the retrieval of all members of a class
 public static ArrayList<StudentClass>getAll(){
    return arStud;
    }// retrieve all 

    //method to determine the student with the highest marks
 public static void getHighestMarks(){
     int pos=-1;
            int hmark=0;
            for (int i = 0; i < arStud.size(); i++)
            {
            if(arStud.get(i).getMarks()>hmark){
            hmark=arStud.get(i).getMarks();
            pos=i;
            }
            }
           JOptionPane.showMessageDialog(null,"Stud No: "+arStud.get(pos).getStdno()+" has the highest marks of: "+hmark);
    }//highest

    //method to count the number of student doing ASDSY3A subject
 public static int countASDSY3A(){
    int cntASDY3A=0;
             for (int i = 0; i < arStud.size(); i++)
            {
            if(arStud.get(i).getSubjcode().equals("ASDSY3A")){
            cntASDY3A+=1;
            }
            }
             return cntASDY3A;
    }//cnt

}//end DA class
