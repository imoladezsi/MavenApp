package Service.Integration;

import Exceptions.ValidatorException;
import Repository.XMLFileRepository.NotaXMLRepo;
import Repository.XMLFileRepository.StudentXMLRepo;
import Repository.XMLFileRepository.TemaLabXMLRepo;
import Service.StudentService.XMLFileService.NotaXMLService;
import Service.StudentService.XMLFileService.StudentXMLService;
import Service.StudentService.XMLFileService.TemaLabXMLService;
import Validator.NotaValidator;
import Validator.StudentValidator;
import Validator.TemaLabValidator;
import org.junit.Test;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class TopDownTesting {
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

    //test case fo addStudent
    @Test
    public void shouldPass_addStudent()
    {
        String[] params={"1aj31","Nume Prenume","933","email@scs.ubbcluj.ro","Nume Prenume"};
        shouldPassStudent(params);
    }

    //integration test for addAssignment (addStudent + addAssignment)
    @Test
    public void shouldPass_integration_addStudent_addAssignment()
    {
        StudentValidator vs=new StudentValidator();
        TemaLabValidator vt=new TemaLabValidator();
       // NotaValidator vn=new NotaValidator();
        StudentXMLRepo strepo=new StudentXMLRepo(vs,"StudentiXML.xml");
        TemaLabXMLRepo tmrepo=new TemaLabXMLRepo(vt,"TemaLaboratorXML.xml");
       // NotaXMLRepo ntrepo=new NotaXMLRepo(vn,"NotaXML.xml");
        StudentXMLService stsrv=new StudentXMLService(strepo);
        TemaLabXMLService tmsrv=new TemaLabXMLService(tmrepo);
       // NotaXMLService ntsrv=new NotaXMLService(ntrepo);
        NotaXMLService ntsrv = mock(NotaXMLService.class);

        String[] paramsStudent={"1aj31","Nume Prenume","933","email@scs.ubbcluj.ro","Nume Prenume"};
        String[] paramsAssignment={"1","text","5","7"};
        String[] paramsGrade={"1","text","5","5.5", LocalDateTime.now().toString()};

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
        assertNotNull(stsrv.findOne("1aj31"));
        assertNotNull(tmsrv.findOne(1));
    }

    //integration test for addGrade (addStudent + addAssignment + addGrade)
    @Test
    public void shouldPass_integration_addStudent_addAssignment_addGrade()
    {
        StudentValidator vs=new StudentValidator();
        TemaLabValidator vt=new TemaLabValidator();
        NotaValidator vn=new NotaValidator();
        StudentXMLRepo strepo=new StudentXMLRepo(vs,"StudentiXML.xml");
        TemaLabXMLRepo tmrepo=new TemaLabXMLRepo(vt,"TemaLaboratorXML.xml");
        NotaXMLRepo ntrepo=new NotaXMLRepo(vn,"NotaXML.xml");
        StudentXMLService stsrv=new StudentXMLService(strepo);
        TemaLabXMLService tmsrv=new TemaLabXMLService(tmrepo);
        NotaXMLService ntsrv=new NotaXMLService(ntrepo);

        String[] paramsStudent={"1aj31","Nume Prenume","933","email@scs.ubbcluj.ro","Nume Prenume"};
        String[] paramsAssignment={"1","text","5","7"};
        String[] paramsGrade={"1","text","5","5.5", LocalDateTime.now().toString()};

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
        assertNotNull(stsrv.findOne("1aj31"));
        assertNotNull(tmsrv.findOne(1));
        assertNotNull(ntsrv.findOne(1));
    }
}
