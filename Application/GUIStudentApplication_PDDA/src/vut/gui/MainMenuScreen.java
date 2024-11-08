/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vut.gui;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import vut.data.*;

/**
 *
 * @author user
 */
public class MainMenuScreen extends JFrame
{
//DECLARING ALL MENUITEMS
    JMenuItem miaddStud,midisplay,misearch,micount,mihighest,miexit,miupdate,midelete;
    //declaring menus
    JMenu mnFile,mnStud;
    //DECLARE ARRAYLIST 
    private ArrayList<StudentClass> arStud;
    public MainMenuScreen(){  
     //CREATE ARRAYLIST OBJECT
     arStud=new ArrayList<>();
    //create menuitems objects
    miaddStud=new JMenuItem("Add New Stud");
    midisplay=new JMenuItem("Display All Stud Records");
    misearch=new JMenuItem("Search by Stud No");
    micount=new JMenuItem("Count Studs doing ASDSY3A Subj");
    mihighest=new JMenuItem("Get Stud with Highest Mark");
    miexit=new JMenuItem("Exit Application");
    miupdate=new JMenuItem("Update Stud Marks");
    midelete=new JMenuItem("Delete Stud Record");
    //CREATE MENU OBJECTS AND ADD MENUITEMS TO MENU OBJECTS
    mnFile=new JMenu("File");
    //add items of this menu, according to order of appearance
    mnFile.add(misearch);
    mnFile.add(mihighest);
    mnFile.add(micount);
    mnFile.add(miupdate);
    mnFile.add(midelete);
    mnFile.add(miexit);
    
    mnStud=new JMenu("Student");
    //add items of this menu, according to order of appearance
    mnStud.add(miaddStud);
    mnStud.add(midisplay);
    //CREATE MENUBAR OBJECTS AND ADD MENUS  TO MENUBAR OBJECTS
    JMenuBar jmb=new JMenuBar();
    //add menus in the menubar, according to order of appearance
    jmb.add(mnFile);
    jmb.add(mnStud);
    //CALL SETJMENUBAR METHOD, PASSING MENUBAR OBJECT - TO MAKE ALL MENUS/THEIR ITEMS APPEAR
        setJMenuBar(jmb);
        
     //REGISTER ALL MENUITEMS EVENTS 
     miaddStud.addActionListener(new miAddStudEvent());
     midisplay.addActionListener(new miDisplayEvent());
     misearch.addActionListener(new miSearchEvent());
     micount.addActionListener(new miCountEvent());
     mihighest.addActionListener(new miHighestEvent());
     miexit.addActionListener(new miExitEvent());
     miupdate.addActionListener(new miUpdateEvent());
     midelete.addActionListener(new miDeleteEvent());
     
    //call PD method at the start of the  application(ie menu constructor), and handle DataStorageException
     try{
     StudentClass.initialize(); //call pd method
     }catch(DataStorageException ex){
     JOptionPane.showMessageDialog(null,ex.getMessage());
     }
    }//end constructor
    
    //CREATE MENUITEMS EVENTS USING INNER CLASS FOR EACH MENUITEM
    //inner class to create addstud item event
    private class miAddStudEvent implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e)
        {
            //CALLING STUDENT SCREEN TO OPEN
        StudInfo frm=new StudInfo(arStud);
        frm.setVisible(true);
        frm.setTitle("STUDENT SCREEN BY MS MNGOMA");
        frm.setSize(400,300);  
        frm.setResizable(false);//we want this screen to have a fixed size   
        }
    
    
    }//add item
      //inner class to create display item event
    private class miDisplayEvent implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e)
        {
            //CALLING DISPLAY SCREEN TO OPEN
        DisplayScreen frm=new DisplayScreen(arStud);
        frm.setVisible(true);
        frm.setTitle("DISPLAY SCREEN BY MS MNGOMA");
        frm.setSize(400,300);
        frm.setResizable(false);//we want this screen to have a fixed size
            
        }
    
    
    }//display item
      //inner class to create search item event
    private class miSearchEvent implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e)
        {

            try{
            String searchStuNo=JOptionPane.showInputDialog("Enter Stud No to search");
            StudentClass stud=StudentClass.findStud(searchStuNo);//call pd method
            JOptionPane.showMessageDialog(null,stud);
            }catch(NotFoundException ex){
            JOptionPane.showMessageDialog(null,ex.getMessage());
            }
            
//        
        }
    
    
    }//search item
          //inner class to create update item event
    private class miUpdateEvent implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e)
        {
            //How to call the update method (assume we need to find the stud record first),
            // it also need to handle NotFoundException
            try{
            String searchStuNo=JOptionPane.showInputDialog("Enter Stud No to search");//input for search
            int newmarks=Integer.parseInt( JOptionPane.showInputDialog("Enter new marks"));//input for new value
            StudentClass stud=StudentClass.findStud(searchStuNo);//call pd method to find stud record first
            stud.updateMarks(newmarks); //call pd method to update the field of a found stud
            }catch(NotFoundException ex){
            JOptionPane.showMessageDialog(null,ex.getMessage());
            }
            
     }
    }//update item
              //inner class to create delete item event
    private class miDeleteEvent implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e)
        {
          //handle exception,since da and call pd methods throws exception
            try{
            String searchStuNo=JOptionPane.showInputDialog("Enter Stud No to search");
            
            StudentClass stud=StudentClass.findStud(searchStuNo);//call pd method
            stud.deleteStud(); //use found stud  to delete the record
            }catch(NotFoundException ex){
            JOptionPane.showMessageDialog(null,ex.getMessage());
            }
            
     }
    }//delete item
   
      //inner class to create highest item event
    private class miHighestEvent implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e)
        {
         
           //call PD method for highest
            StudentClass.getHighestMarks();
        }
    
    
    }//highest item
    
      //inner class to create count item event
    private class miCountEvent implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e)
        {
            //call PD method to count  
            int cntASDY3A=StudentClass.countASDSY3A();

           JOptionPane.showMessageDialog(null,"No of studs in ASDSY3A: "+cntASDY3A); 
        }
     }//count item
    
      //inner class to create exit item event
    private class miExitEvent implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e)
        {
            //call pd method to save data to the file, when the program exits
            //handle DataStorageExcepion
            try{
                 int resp;
                    
           resp=JOptionPane.showConfirmDialog(null, "Do u want to exit?","Confirm",JOptionPane.YES_NO_OPTION);
           if(resp==JOptionPane.YES_OPTION){
               
                StudentClass.terminate();//call pd method
                System.exit(0);
        }else
              
           mnStud.requestFocus();
           
        
     }catch(DataStorageException ex){
     JOptionPane.showMessageDialog(null,ex.getMessage());
     }
            
  }      
 }//exit item
    
    //execute this class
    public static void main(String[] args)
    {
        MainMenuScreen frm=new MainMenuScreen();
        frm.setVisible(true);
        frm.setTitle("MAIN MENU SCREEN BY MS MNGOMA");
        frm.setSize(400,300);
        frm.setDefaultCloseOperation(EXIT_ON_CLOSE);  
    }     
}
