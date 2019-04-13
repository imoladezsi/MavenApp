package Service.StudentService.XMLFileService;

import Domain.TemaLab;
import Exceptions.ValidatorException;
import Repository.XMLFileRepository.TemaLabXMLRepo;

public class TemaLabXMLService extends AbstractXMLService<Integer,TemaLab>{
    private TemaLabXMLRepo xmlrepo;

    public TemaLabXMLService(TemaLabXMLRepo xmlrepo)  {
        super(xmlrepo);
    }

    public void prelungireTemaLab(String nr,String descr,String sl,String sp,int sc) throws ValidatorException {
        try {
            if (sc <= Integer.parseInt(sp)) {
                String sln = Integer.toString(Integer.parseInt(sl) + 1);
                String[] params = {nr, descr, sln, sp};
                update(params);
            }
        }catch(NumberFormatException ne){
            throw new ValidatorException("Weeks must be numbers");
        }

    }
    @Override
    protected TemaLab extractEntity(String[] params) throws ValidatorException{
        try {
            return new TemaLab(Integer.parseInt(params[0]), params[1], Integer.parseInt(params[2]), Integer.parseInt(params[3]));

        }catch(NumberFormatException ne){
            throw new ValidatorException("Assignment id and weeks must be numbers");

        }
    }

}