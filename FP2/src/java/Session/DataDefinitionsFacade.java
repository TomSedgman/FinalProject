/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Entities.DataDefinitions;
import Entities.Nodes;
import Entities.Projects;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author t_sedgman
 */
@Stateless
public class DataDefinitionsFacade extends AbstractFacade<DataDefinitions> {
    @PersistenceContext(unitName = "FP2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DataDefinitionsFacade() {
        super(DataDefinitions.class);
    }
    
    public List variablesList(String nTIdentifier)
    {
        List<DataDefinitions> dataDefinitions= em.createNamedQuery("findFieldsByName", DataDefinitions.class).setParameter("nTIdentifier",nTIdentifier).getResultList();
        
        return dataDefinitions;
    }
    public String getDataType(Long projectId, int variable)
    {
        DataDefinitions dataDefinition=em.createNamedQuery("findTypebyName", DataDefinitions.class).setParameter("projectId",projectId).setParameter("variable",variable).getSingleResult();
        String data = dataDefinition.getdDType();
        return data;
    }
     public List allDefinitions(Projects project)
    {
        List<DataDefinitions> dataDefinitions= em.createNamedQuery("findAllDefinitionsbyProject", DataDefinitions.class).setParameter("project",project).getResultList();
        
        return dataDefinitions;
    }
}
