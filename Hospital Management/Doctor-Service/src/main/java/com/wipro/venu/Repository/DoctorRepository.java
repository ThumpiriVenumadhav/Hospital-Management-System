package com.wipro.venu.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.venu.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}