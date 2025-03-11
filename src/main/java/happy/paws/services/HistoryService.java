package happy.paws.services;

import org.springframework.stereotype.Service;

import happy.paws.model.History;
import happy.paws.repositories.HistoryRepository;

@Service
public class HistoryService {

    private HistoryRepository historyRepository;

    public HistoryService(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public History addHistory(History history,int pet_id){
        history.setPetId(pet_id);
       return historyRepository.save(history);
    }

}
