/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vut.gui;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import vut.data.DuplicateException;
//need to import package that has StudentClass
import vut.data.StudentClass;

/**
 *
 * @author user 
 */
public class StudInfo extends JFrame
{
    JLabel lblsno=new JLabel("Stud No:");
   JLabel lblnm=new JLabel("Stud Name:");
   JLabel lblgender=new JLabel("Gender:");
   JLabel lblscode=new JLabel("SubjCode:");
   JLabel lblmarks=new JLabel("Marks:");
   //input
   JTextField txtsno=new JTextField();
   JTextField txtnm=new JTextField();
   JTextField txtmarks=new JTextField();
   JRadioButton rbFem=new JRadioButton("Female");
   JRadioButton rbMale=new JRadioButton("Male");
    JComboBox<String> cmb=new JComboBox<>();
    JButton btnAdd=new JButton("Add Stud");
    JButton btnClear=new JButton("Clear");
    JButton btnClose=new JButton("Close");
    //JTextArea taDisp=new JTextArea();
    
   //declare arraylist
    private ArrayList<StudentClass> arStud;
    public StudInfo(ArrayList<StudentClass> arStud){
        this.arStud=arStud;
   // taDisp.setEditable(false);
    cmb.addItem("ASDSY3A");
    cmb.addItem("ASDSY1A");
    cmb.addItem("AIWMX2A");
    cmb.addItem("AIBAX3A");
    JPanel pnl=new JPanel();
    pnl.setLayout(null);
    pnl.add(lblsno); pnl.add(lblnm); pnl.add(lblgender); pnl.add(lblscode);
     pnl.add(lblmarks); pnl.add(txtsno);pnl.add(txtnm);pnl.add(txtmarks);
     pnl.add(rbFem);pnl.add(rbMale);pnl.add(cmb);pnl.add(btnClear);
     pnl.add(btnAdd);pnl.add(btnClose);//pnl.add(taDisp);
     //setbound
     lblsno.setBounds(10,20,140,20);     txtsno.setBounds(100,20,120,20);
     lblnm.setBounds(10,50,140,20);     txtnm.setBounds(100,50,120,20);
     lblgender.setBounds(10,80,140,20);  rbFem.setBounds(100,80,100,20);rbMale.setBounds(210,80,100,20);
     lblscode.setBounds(10,110,140,20);  cmb.setBounds(100,110,120,20);
     lblmarks.setBounds(10,140,140,20);  txtmarks.setBounds(100,140,120,20);
     
     //buttons
     btnAdd.setBounds(10,180,100,20);  btnClear.setBounds(140,180,100,20);btnClose.setBounds(270,180,100,20);
     //textarea for displaying
    // taDisp.setBounds(10,210,380,280);
    
     //set contentpane to make objects show
        setContentPane(pnl);
        
        //register button events
        btnAdd.addActionListener(new btnAddEvent());
        btnClear.addActionListener(new btnClearEvent());
        btnClose.addActionListener(new btnCloseEvent());
    }
    //create button events
    private class btnAddEvent implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e)
        {
            //handle exception to check for duplicates
            try{
                     
             StudentClass objClass=getStudInfo();//call method with all inputs
             objClass.addNew();//call pd method to add new stud to arraylist
            }catch(DuplicateException ex){
            JOptionPane.showMessageDialog(null,ex.getMessage());
            }
   
   
   
      
        }
    
    }
    private class btnClearEvent implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e)
        {
      txtsno.setText("");
      txtsno.requestFocus();
      txtnm.setText("");
      txtmarks.setText("");
      rbFem.setSelected(false);
      rbMale.setSelected(false);
      cmb.setSelectedIndex(0);
            
        }
    
    }
     private class btnCloseEvent implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e)
        {
    //closing only this screen
           dispose();
        }
    
    }
    
    //my method to get all inputs - you can still do it within btnAddEvent
    public StudentClass getStudInfo(){
       String sno,nm,gender,scode;
      int marks; 
      sno=txtsno.getText();
      nm=txtnm.getText();
      scode=cmb.getSelectedItem().toString();
      marks=Integer.parseInt(txtmarks.getText());
      if(rbFem.isSelected()){
      gender=rbFem.getText();
      }
      else{
      gender=rbMale.getText();
      }
    StudentClass objStud=new StudentClass(sno, nm, gender, scode, marks);
    return objStud;
    }
    
    
    

    
}
