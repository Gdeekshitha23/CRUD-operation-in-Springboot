package com.xadmin.SpringBootCrud.service;

import com.xadmin.SpringBootCrud.bean.Subject;
import com.xadmin.SpringBootCrud.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {
    @Autowired
    public SubjectRepository subjectRepo;
    public List<Subject> getAllSubjects(){
        List<Subject> subjects = new ArrayList<>();
        subjectRepo.findAll().forEach(subjects::add);
        return subjects;
    }

    public String addSubject(Subject subject) {
        if(subjectRepo.existsById(subject.getId()))
        {
            return "Subject Already Present";
        }
        subjectRepo.save(subject);
        return "Subject Added Successfully";

    }
    public String updateSubject(String id ,Subject subject) {
        Optional<Subject> existingSubjectOptional = subjectRepo.findById(id);

        if (existingSubjectOptional.isPresent()) {
            Subject existingSubject = existingSubjectOptional.get();
            if(!(existingSubject.getId().equals(subject.getId()))){
                return "Cannot update Id";
            }
            else if((!(existingSubject.getSubjectName().equals(subject.getSubjectName()))) && (!(existingSubject.getTeacherName().equals(subject.getTeacherName())))){
                return "Subject name and Teacher name updated";
            }
            else if(!(existingSubject.getSubjectName().equals(subject.getSubjectName()))){
                return "Subject name updated";
            }
            else if(!(existingSubject.getTeacherName().equals(subject.getTeacherName()))){
                return "Teacher name updated";
            }
            subjectRepo.save(subject);
        }
        return "Id not present";
    }
    public boolean deleteSubject(String id) {
        if(subjectRepo.existsById(id)) {
            subjectRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
