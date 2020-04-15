package rest;

import com.google.gson.Gson;
import dto.ChuckJokeDTO;
import dto.DadJokeDTO;
import dto.ApiDTO;
import dto.CopenhagenTimeDTO;
import dto.CountryDTO;
import entities.User;
import java.io.IOException;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import utils.EMF_Creator;
import utils.HttpUtils;

/**
 * @author lam@cphbusiness.dk
 */
@Path("info")
public class DemoResource {

    private static EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);

    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    private final static Gson GSON = new Gson();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoForAll() {
        return "{\"msg\":\"Hello anonymous\"}";
    }

    //Just to verify if the database is setup
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public String allUsers() {

        EntityManager em = EMF.createEntityManager();
        try {
            List<User> users = em.createQuery("select user from User user").getResultList();
            return "[" + users.size() + "]";
        } finally {
            em.close();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("user")
    @RolesAllowed("user")
    public String getFromUser() {
        String thisuser = securityContext.getUserPrincipal().getName();
        return "{\"msg\": \"Hello to User: " + thisuser + "\"}";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("admin")
    @RolesAllowed("admin")
    public String getFromAdmin() {
        String thisuser = securityContext.getUserPrincipal().getName();
        return "{\"msg\": \"Hello to (admin) User: " + thisuser + "\"}";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("external")
    public String getFromExternalAPI() {
        try {
            String chuckString = HttpUtils.fetchData(ChuckJokeDTO.getRANDOM_URL());
            ChuckJokeDTO chuck = GSON.fromJson(chuckString, ChuckJokeDTO.class);

            String dadString = HttpUtils.fetchData(DadJokeDTO.getRANDOM_URL());
            DadJokeDTO dad = GSON.fromJson(dadString, DadJokeDTO.class);

            String countryString = HttpUtils.fetchData(CountryDTO.getCOUNTRY_URL());
            CountryDTO[] country = GSON.fromJson(countryString, CountryDTO[].class);
            
            String timeString = HttpUtils.fetchData(CopenhagenTimeDTO.getTIME_URL());
            CopenhagenTimeDTO time = GSON.fromJson(timeString, CopenhagenTimeDTO.class);

            ApiDTO apis = new ApiDTO(chuck, dad, country[0], time);
            return GSON.toJson(apis);
        } catch (IOException ex) {
            return "{\"info\":\"Error\"}";
        }
    }
}
