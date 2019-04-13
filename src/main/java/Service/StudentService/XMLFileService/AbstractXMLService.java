package Service.StudentService.XMLFileService;
import Domain.*;
import Exceptions.ValidatorException;
import Repository.XMLFileRepository.AbstractXMLRepo;

import java.text.NumberFormat;

public abstract class AbstractXMLService<ID,E extends HasId<ID>> {
    private AbstractXMLRepo xmlrepo;

    public AbstractXMLService(AbstractXMLRepo xmlrepo)  {
        this.xmlrepo = xmlrepo;
    }

    protected abstract E extractEntity(String[] params) throws ValidatorException;
        //return new Student(params[0],params[1],Integer.parseInt(params[2]),params[3],params[4]);
    //}

    public void add(String params[]) throws ValidatorException{
        E e = extractEntity(params);
        E f = findOne(e.getId());
        if (f ==null)
            xmlrepo.save(e);
        else throw new ValidatorException("Id must be unique");
    }
    public void remove(ID id){
        xmlrepo.delete(id);
    }
    public void update(String params[]) throws ValidatorException{
        E s = extractEntity(params);
        xmlrepo.update(s);
    }
    public E findOne(ID id){
        return (E) xmlrepo.findOne(id);
    }
    public Iterable<E>findAll(){
        return xmlrepo.findAll();
    }
    public int getSize(){
        return xmlrepo.getSize();
    }
}