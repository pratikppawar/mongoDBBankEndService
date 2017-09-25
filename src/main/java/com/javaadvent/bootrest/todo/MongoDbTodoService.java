package com.javaadvent.bootrest.todo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * This service class saves {@link com.javaadvent.bootrest.todo.Todo} objects
 * to MongoDB database.
 * @author Petri Kainulainen
 */
@Service
final class MongoDBTodoService implements AttendeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MongoDBTodoService.class);

    private final AttendeeRepository repository;

    @Autowired
    MongoDBTodoService(AttendeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public AttendeeDTO create(AttendeeDTO todo) {
        LOGGER.info("Creating a new todo entry with information: {}", todo);

        Attendee persisted = Attendee.getBuilder()
        		.rfid(todo.getRfid())
                .name(todo.getName())
                .email(todo.getEmail())
                .orgName(todo.getOrgName())
                .build();

        persisted = repository.save(persisted);
        LOGGER.info("Created a new todo entry with information: {}", persisted);

        return convertToDTO(persisted);
    }

    @Override
    public AttendeeDTO delete(String id) {
        LOGGER.info("Deleting a todo entry with id: {}", id);

        Attendee deleted = findTodoById(id);
        repository.delete(deleted);

        LOGGER.info("Deleted todo entry with informtation: {}", deleted);

        return convertToDTO(deleted);
    }

    @Override
    public List<AttendeeDTO> findAll() {
        LOGGER.info("Finding all todo entries.");

        List<Attendee> todoEntries = repository.findAll();

        LOGGER.info("Found {} todo entries", todoEntries.size());

        return convertToDTOs(todoEntries);
    }

    private List<AttendeeDTO> convertToDTOs(List<Attendee> models) {
        return models.stream()
                .map(this::convertToDTO)
                .collect(toList());
    }

    @Override
    public AttendeeDTO findById(String id) {
        LOGGER.info("Finding todo entry with id: {}", id);

        Attendee found = findTodoById(id);

        LOGGER.info("Found todo entry: {}", found);

        return convertToDTO(found);
    }

    @Override
    public AttendeeDTO update(AttendeeDTO todo) {
        LOGGER.info("Updating todo entry with information: {}", todo);

        Attendee updated = findByEmail(todo.getEmail());
        updated.update(todo.getRfid(), todo.getName(), todo.getEmail(), todo.getOrgName());
        updated = repository.save(updated);

        LOGGER.info("Updated todo entry with information: {}", updated);

        return convertToDTO(updated);
    }

    private Attendee findTodoById(String id) {
        Optional<Attendee> result = repository.findOne(id);
        return result.orElseThrow(() -> new TodoNotFoundException(id));

    }

    private AttendeeDTO convertToDTO(Attendee model) {
    	AttendeeDTO dto = new AttendeeDTO();

        dto.setRfid(model.getrfid());
        dto.setEmail(model.getEmail());
        dto.setName(model.getName());
        dto.setOrgName(model.getOrgName());

        return dto;
    }

	@Override
	public Attendee findByEmail(String email) {
		// TODO Auto-generated method stub
		Attendee att=repository.findByEmail(email);
		if(att!=null){
			return att;
		}
		return null;
	}
}
