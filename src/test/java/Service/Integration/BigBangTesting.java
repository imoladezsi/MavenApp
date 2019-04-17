package Service.Integration;

import Exceptions.ValidatorException;
import Repository.XMLFileRepository.TemaLabXMLRepo;
import Service.StudentService.XMLFileService.TemaLabXMLService;
import Validator.TemaLabValidator;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

public class BigBangTesting {

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


}
