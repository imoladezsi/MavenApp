package Service.AssignmentService;
import Domain.TemaLab;
import Exceptions.ValidatorException;
import Repository.XMLFileRepository.TemaLabXMLRepo;
import Service.StudentService.XMLFileService.TemaLabXMLService;
import Validator.TemaLabValidator;
import org.junit.Test;

import static org.junit.Assert.*;

/*
Notations:
    SC = Statement Coverage
    PC = Path Coverage
    CDC = Condition/Decision Coverage
 */
public class TestAddAssignment {


    void shouldFail(String[] params){

        TemaLabValidator vt=new TemaLabValidator();
        TemaLabXMLRepo tmrepo=new TemaLabXMLRepo(vt, "TemaLaboratorXML.xml");
        int id = -1;
        try {
            id = Integer.parseInt(params[0]);
        }catch(NumberFormatException fe){
            fail();
        }
        tmrepo.delete(id);
        assertNull(tmrepo.findOne(id));
        TemaLabXMLService tmsrv=new TemaLabXMLService(tmrepo);
        boolean gotHere = false;
        try {
            tmsrv.add(params);
            fail();
        }catch(ValidatorException e){
            TemaLab tema = tmsrv.findOne(id);
            assertNull(tema);
            gotHere = true;
        }
        assertTrue(gotHere);
    }

    void shouldFail(String[] params, String fileName){


        if (fileName == null)
            fileName = "TemaLaboratorXML.xml";
        TemaLabValidator vt=new TemaLabValidator();
        TemaLabXMLRepo tmrepo=new TemaLabXMLRepo(vt, fileName);
        int id = -1;
        try {
            id = Integer.parseInt(params[0]);
        }catch(NumberFormatException fe){
            fail();
        }
        tmrepo.delete(id);
        assertNull(tmrepo.findOne(id));
        TemaLabXMLService tmsrv=new TemaLabXMLService(tmrepo);
        boolean gotHere = false;
        try {
            tmsrv.add(params);
            fail();
        }catch(ValidatorException e){
            TemaLab tema = tmsrv.findOne(id);
            assertNull(tema);
            gotHere = true;
        }
        assertTrue(gotHere);
    }

    void shouldPass(String[] params, String fileName){
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

    void shouldPass(String[] params){
        TemaLabValidator vt=new TemaLabValidator();
        TemaLabXMLRepo tmrepo=new TemaLabXMLRepo(vt, "TemaLaboratorXML.xml");
        TemaLabXMLService tmsrv=new TemaLabXMLService(tmrepo);
        boolean gotHere = false;
        try {
            tmrepo.delete(Integer.parseInt(params[0]));
        }catch(NumberFormatException fe){
            fail();
        }
        try {
            tmsrv.add(params);
            gotHere = true;
        }catch(ValidatorException e){
            fail();
        }
        assertTrue(gotHere);
    }

    @Test
    public void testFindAllAndDelete(){
        TemaLabValidator vt=new TemaLabValidator();
        TemaLabXMLRepo tmrepo=new TemaLabXMLRepo(vt,"TemaLaboratorXML.xml");
        TemaLabXMLService tmsrv=new TemaLabXMLService(tmrepo);
        tmrepo.findAll().forEach(x->{
            tmrepo.delete(x.getId());

        });
        assertEquals(tmrepo.findAll().spliterator().getExactSizeIfKnown(), 0L);
        try {
            tmrepo.save(new TemaLab(1, "text", 6, 7));
            ;
        }catch(ValidatorException vex)
        {
            fail();
        }
        assertEquals(tmrepo.findAll().spliterator().getExactSizeIfKnown(), 1L);

    }

    @Test
    public void testFindOne(){
        TemaLabValidator vt=new TemaLabValidator();
        TemaLabXMLRepo tmrepo=new TemaLabXMLRepo(vt,"TemaLaboratorXML.xml");
        TemaLabXMLService tmsrv=new TemaLabXMLService(tmrepo);
        String[] params = {"1", "text", "5", "7"};
        try {
            tmsrv.add(params);
        }catch(ValidatorException vex){
            fail();
        }
        assertEquals(tmrepo.findAll().spliterator().getExactSizeIfKnown(), 1L);
        assertTrue(tmrepo.findOne(1).getId()== 1);

    }

    @Test
    public void testAddAssignment_notUniqueId() {
        // contributes to SC
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
        // contributes to SC
        // covers in two methods
        String[] params={"1","text","5","7"};
        shouldPass(params, null);
    }

    @Test
    public void shouldPass_SC_canExtractEntity(){
        // where id and weeks are integers
        String[] params={"1","text","5","7"};
        shouldPass(params, null);
    }


    @Test
    public void shouldFail_CDC_cannotExtractEntity(){
        // where id or one of the weeks are not integers
        String[] params={"NotANumber","text","5","7"};
        TemaLabValidator vt=new TemaLabValidator();
        TemaLabXMLRepo tmrepo=new TemaLabXMLRepo(vt, "TemaLaboratorXML.xml");
        TemaLabXMLService tmsrv=new TemaLabXMLService(tmrepo);
        boolean gotHere = false;
        try {
            tmsrv.add(params);
            fail();
        }catch(ValidatorException e){

            gotHere = true;
        }
        assertTrue(gotHere);
    }

    @Test
    public void shouldPass_SC_writeToNewFile(){
        String[] params={"1","text","5","7"};
        shouldPass(params, "NewAssignmentFile.XML");
    }

    @Test
    public void shouldFail_SC_tooFewArguments(){
        String[] params={"1"};
        shouldFail(params);
    }

    @Test
    public void shouldPass_PC_loopWriteSeveralItems(){
        TemaLabValidator vt=new TemaLabValidator();
        TemaLabXMLRepo tmrepo=new TemaLabXMLRepo(vt,"TemaLaboratorXML.xml");
        TemaLabXMLService tmsrv=new TemaLabXMLService(tmrepo);
        String[] params={"1","text","5","7"};
        String[] params2={"2","text","5","7"};
        boolean gotHere = false;
        try {
            tmsrv.add(params);
            tmsrv.add(params2);
            gotHere = true;
        }catch(ValidatorException e){
            fail();
        }
        assertTrue(gotHere);

    }
}
