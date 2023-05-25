package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "log_table")
public class RequestResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "LONGTEXT")
    private String requestMethod;
    @Column(columnDefinition = "LONGTEXT")
    private String requestUrl;
    @Column(columnDefinition = "LONGTEXT")
    private String requestBody;
    @Column(columnDefinition = "LONGTEXT")
    private String response;
}
