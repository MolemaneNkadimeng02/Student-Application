/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vut.data;

import java.io.Serializable;
import java.util.ArrayList;

public class StudentClass implements Serializable
{
  private String stdno; 
    private String stuname,gender,subjcode;
    private int marks;
    //constuctor
    public StudentClass(String stdno,String stuname,String gender,String subjcode,int marks){
        setStdno(stdno);
        setStuname(stuname);
        setGender(gender);
        setSubjcode(subjcode);
        setMarks(marks);
    }

    public String getStdno()
    {
        return stdno;
    }

    public void setStdno(String stdno)
    {
        this.stdno = stdno;
    }

    public String getStuname()
    {
        return stuname;
    }

    public void setStuname(String stuname)
    {
        this.stuname = stuname;
    }

    public String getGender()
    {
        return gender;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }

    public String getSubjcode()
    {
        return subjcode;
    }

    public void setSubjcode(String subjcode)
    {
        this.subjcode = subjcode;
    }

    public int getMarks()
    {
        return marks;
    }

    public void setMarks(int marks)
    {
        this.marks = marks;
    }
  @Override
  public String toString(){
  
      return stdno+"\t"+stuname+"\t"+gender+"\t"+subjcode+"\t"+marks+"\n";
  }

  //DA METHODS
  public static void initialize()throws DataStorageException{
    StudentDA.initialize();
  }
  public static void terminate()throws DataStorageException{
    StudentDA.terminate();
  }
  public void addNew()throws DuplicateException{
     StudentDA.addNew(this);
  
  }
  public  void updateMarks(int newmarks)throws NotFoundException{
      StudentDA.updateMarks(this, newmarks);
  }
  public static StudentClass findStud(String stdno)throws NotFoundException{
    return StudentDA.findStud(stdno);
  }
  
  public  void deleteStud() throws NotFoundException{
  StudentDA.deleteStud(this);
  }
  public static ArrayList<StudentClass>getAll(String scode){
    return StudentDA.getAll(scode);
  }
    public static ArrayList<StudentClass>getAll(){
    return StudentDA.getAll();
  }
 public static void getHighestMarks(){
    StudentDA.getHighestMarks();
 }
public static int countASDSY3A(){
    return StudentDA.countASDSY3A();
} 
}//end PD class
