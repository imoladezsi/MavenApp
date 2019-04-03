package Service.AssignmentService;
import Exceptions.ValidatorException;
import Repository.XMLFileRepository.TemaLabXMLRepo;
import Service.StudentService.XMLFileService.TemaLabXMLService;
import Validator.TemaLabValidator;
import org.junit.Test;

import static org.junit.Assert.*;


public class TestAddAssignment {

    @Test
    public void testAddAssignment_notUniqueId() {
        TemaLabValidator vt=new TemaLabValidator();
        TemaLabXMLRepo tmrepo=new TemaLabXMLRepo(vt,"TemaLaboratorXML.xml");
        TemaLabXMLService tmsrv=new TemaLabXMLService(tmrepo);
        String[] params={"1","text","5","7"};
        boolean gotHere = false;
        try {
            tmsrv.add(params);
            tmsrv.add(params);
            fail();
        }catch(ValidatorException e){

            gotHere = true;
        }
           assertTrue(gotHere);
    }

    @Test
    public void testAddAssignment_uniqueId() {
        TemaLabValidator vt=new TemaLabValidator();
        TemaLabXMLRepo tmrepo=new TemaLabXMLRepo(vt,"TemaLaboratorXML.xml");
        TemaLabXMLService tmsrv=new TemaLabXMLService(tmrepo);
        String[] params={"1","text","5","7"};
        boolean gotHere = false;
        try {
            tmsrv.add(params);
            gotHere = true;
        }catch(ValidatorException e){

            fail();

        }
        assertTrue(gotHere);
    }
}
