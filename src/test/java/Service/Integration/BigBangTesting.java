package Service.Integration;

import Exceptions.ValidatorException;
import Repository.XMLFileRepository.NotaXMLRepo;
import Repository.XMLFileRepository.StudentXMLRepo;
import Repository.XMLFileRepository.TemaLabXMLRepo;
import Service.StudentService.XMLFileService.NotaXMLService;
import Service.StudentService.XMLFileService.StudentXMLService;
import Service.StudentService.XMLFileService.TemaLabXMLService;
import UI.ui;
import Validator.NotaValidator;
import Validator.StudentValidator;
import Validator.TemaLabValidator;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

public class BigBangTesting {


    public void shouldPassGrade(String[] params){
        NotaValidator vn=new NotaValidator();
        NotaXMLRepo ntrepo=new NotaXMLRepo(vn,"NotaXML.xml");
        NotaXMLService ntsrv=new NotaXMLService(ntrepo);


        try {
            ntsrv.add(params);
            assertNotNull(ntsrv.findOne(Integer.parseInt(params[0])));
        }catch(ValidatorException vex){
            fail();
        }

    }
    public void shouldPassStudent(String[] params)
    {
        StudentValidator vs=new StudentValidator();
        StudentXMLRepo strepo=new StudentXMLRepo(vs,"StudentiXML.xml");
        StudentXMLService stsrv=new StudentXMLService(strepo);
        try{
            stsrv.add(params);
            assertNotNull(stsrv.findOne(params[0]));
        }catch(ValidatorException ex){
            System.out.println(ex.getMessage());
            fail();
        }
    }

    void shouldPassAssignment(String[] params, String fileName){
        int id = -1;
        if (fileName == null)
            fileName = "TemaLaboratorXML.xml";
        TemaLabValidator vt=new TemaLabValidator();
        TemaLabXMLRepo tmrepo=new TemaLabXMLRepo(vt,fileName);
        TemaLabXMLService tmsrv=new TemaLabXMLService(tmrepo);
        boolean gotHere = false;
        try {
            id = Integer.parseInt(params[0]);
        }catch(NumberFormatException fe){
            fail();
        }
        tmrepo.delete(id);
        assertNull(tmrepo.findOne(id));
        try {
            tmsrv.add(params);
            gotHere = true;
        }catch(ValidatorException e){
            fail();
        }
        assertTrue(gotHere);
        assertNotNull(tmsrv.findOne(id));
    }
    @Test
    public void testAddAssignment_uniqueId() {
        // contributes to SC
        // covers in two methods
        String[] params={"1","text","5","7"};
        shouldPassAssignment(params, null);
    }
    @Test
    public void shouldPass_addStudent_Id_ConvertibleAlphaNumeric()
    {
        String[] params={"1aj31","Nume Prenume","933","email@scs.ubbcluj.ro","Nume Prenume"};
        shouldPassStudent(params);
    }

    @Test
    public void shouldPass_addGrade_uniqueId(){
        String[] params={"1","text","5","5.5", LocalDateTime.now().toString()};
        shouldPassGrade(params);
    }

    @Test
    public void integration_addStudent_addGrade_addAssignment(){

        StudentValidator vs=new StudentValidator();
        TemaLabValidator vt=new TemaLabValidator();
        NotaValidator vn=new NotaValidator();
        StudentXMLRepo strepo=new StudentXMLRepo(vs,"StudentiXML.xml");
        TemaLabXMLRepo tmrepo=new TemaLabXMLRepo(vt,"TemaLaboratorXML.xml");
        NotaXMLRepo ntrepo=new NotaXMLRepo(vn,"NotaXML.xml");
        StudentXMLService stsrv=new StudentXMLService(strepo);
        TemaLabXMLService tmsrv=new TemaLabXMLService(tmrepo);
        NotaXMLService ntsrv=new NotaXMLService(ntrepo);

        String[] paramsGrade={"1","text","5","5.5", LocalDateTime.now().toString()};
        String[] paramsStudent={"1aj31","Nume Prenume","933","email@scs.ubbcluj.ro","Nume Prenume"};
        String[] paramsAssignment={"1","text","5","7"};
        boolean here = false;
        try {
            stsrv.add(paramsStudent);
            tmsrv.add(paramsAssignment);
            ntsrv.add(paramsGrade);
            here = true;
        }catch(ValidatorException vex){
            fail();
        }
        assertTrue(here);
    }
}
