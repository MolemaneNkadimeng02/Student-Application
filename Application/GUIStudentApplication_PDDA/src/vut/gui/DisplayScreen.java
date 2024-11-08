/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vut.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.ArrayList;
import javax.swing.*;
//need to import package that has StudentClass
import vut.data.StudentClass;

/**
 *
 * @author user 
 */
public class DisplayScreen extends JFrame
{
   
    JTextArea taDisp=new JTextArea();
    //Note- constructor play the role of form load event,
    //the code to display all records must be done in the constructor ,
    //but to keep our constructor neat, its best to create a method for displaying ,
    //then call that method in the constructor
    public DisplayScreen(ArrayList<StudentClass> arStud){
   
        taDisp.setEditable(false);
         //WHEN USING BORDER LAYOUT TO FILL THE FRAME WITH TEXTAREA
//        Container scrn=getContentPane();
//        scrn.setLayout(new BorderLayout());
//        scrn.add(taDisp,BorderLayout.CENTER);
//        
        //WHEN USING BORDER LAYOUT TO FILL THE FRAME WITH TEXTAREA
        JPanel pnl=new JPanel();
        pnl.setLayout(null);
        pnl.add(taDisp);
        taDisp.setBounds(0,0,400,300);
        setContentPane(pnl);
        //call display method to display stud records
        displayAllStuds(arStud);
    } 
    //method must pass array list, so it will display all loaded  data from arraylist
    public void displayAllStuds(ArrayList<StudentClass> arStud){
         String subcode=JOptionPane.showInputDialog("Enter SubjCode:"); //get subj code input
          if(subcode.isEmpty()){ //Check if input is empty
        //if empty, call pd method to retrieve all studs from arraylist
        arStud= StudentClass.getAll();
          }else{
        //if not empty, call pd method to retrieve all studs from arraylist, for specific input
        arStud=  StudentClass.getAll(subcode); 
          }
     //displaying headings with tabs        
    taDisp.append("StudNo\t"+"Name\t"+"Gender\t"+"SCode\t"+"Marks\n");
    //displaying all records from arraylist
        for (int i = 0; i < arStud.size(); i++)
        {
            taDisp.append(arStud.get(i).toString());
            
        }
    }
}
