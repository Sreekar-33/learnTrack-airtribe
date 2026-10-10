package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.util.IdGenerator;

import java.util.ArrayList;

public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    public Enrollment enrollStudent(int studentId, int courseId, String enrollmentDate) {
        int id = IdGenerator.getNextEnrollmentId();
        Enrollment enrollment = new Enrollment(id, studentId, courseId, enrollmentDate);
        enrollmentRepository.add(enrollment);
        return enrollment;
    }

    public ArrayList<Enrollment> listEnrollments() {
        return enrollmentRepository.findAll();
    }

    public ArrayList<Enrollment> findEnrollmentsByStudentId(int studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }

    public Enrollment findEnrollmentById(int id) throws EntityNotFoundException {
        Enrollment enrollment = enrollmentRepository.findById(id);
        if (enrollment == null) {
            throw new EntityNotFoundException("Enrollment with ID " + id + " not found.");
        }
        return enrollment;
    }

    public void markAsCompleted(int enrollmentId) throws EntityNotFoundException {
        Enrollment enrollment = findEnrollmentById(enrollmentId);
        enrollment.setStatus(EnrollmentStatus.COMPLETED);
    }

    public void markAsCancelled(int enrollmentId) throws EntityNotFoundException {
        Enrollment enrollment = findEnrollmentById(enrollmentId);
        enrollment.setStatus(EnrollmentStatus.CANCELLED);
    }
}
