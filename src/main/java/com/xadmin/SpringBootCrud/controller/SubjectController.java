package com.xadmin.SpringBootCrud.controller;

import com.xadmin.SpringBootCrud.bean.Subject;
import com.xadmin.SpringBootCrud.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class SubjectController {
    @Autowired
    private SubjectService subjectService;
    @RequestMapping(method = RequestMethod.GET,value="/subjects")
    public List<Subject> getAllSubject(){
        return subjectService.getAllSubjects();
    }
    @RequestMapping(method= RequestMethod.POST,value="/subjects")
    public ResponseEntity<Object> addSubject(@RequestBody Subject subject){
        String s=subjectService.addSubject(subject);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                "Message", s));
    }
    @RequestMapping(method= RequestMethod.PUT,value="/subjects/{id}")
    public ResponseEntity<Object> updateSubject(@PathVariable String id , @RequestBody Subject subject){
        String s=subjectService.updateSubject(id,subject);
        if(s.equals("Id not present" )|| s.equals("Cannot update Id")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "Message", s));
        }
        else{
            return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                    "Message", s));
        }
    }
    @RequestMapping(method= RequestMethod.DELETE,value="/subjects/{id}")
    public ResponseEntity<Object> deleteSubject(@PathVariable String id){
        boolean b = subjectService.deleteSubject(id);
        if(b) {
            return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                    "Message", "Deleted Successfully"));
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "Message", "Id not found"));
        }

    }

}
