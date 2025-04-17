package happy.paws.services;

import org.springframework.stereotype.Service;

import happy.paws.model.Request;
import happy.paws.repositories.RequestRepository;

@Service
public class RequestService {

    private RequestRepository requestRepository;

    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public Request crear(Request request){
        return requestRepository.save(request);
    }

}
