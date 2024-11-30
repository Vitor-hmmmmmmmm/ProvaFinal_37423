package com.fiec.provafinal;

import com.fiec.provafinal.models.SapatoRepositorio;
import com.fiec.provafinal.models.sapato;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/sapato")
public class SapatoServlet extends HttpServlet {

    private SapatoRepositorio sapatoRepositorio;

    public SapatoServlet(){
         EntityManagerFactory emf = Persistence.createEntityManagerFactory("minhaunidadepersistencia");
         EntityManager em = emf.createEntityManager();
    }
    // Create  /sapato
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nome = request.getParameter("nome");
        String preco = request.getParameter("preco");
        String imagem = request.getParameter("imagem");
        String tamanho = request.getParameter("tamanho");
        String marca = request.getParameter("marca");
        sapato p = sapato.builder()
                .nome(nome)
                .preco(Double.valueOf(preco))
                .imagemUrl(imagem)
                .tamanho(Integer.valueOf(tamanho))
                .marca(marca)
                .build();


        this.sapatoRepositorio.criar(p);
        response.setContentType("text/html");
        response.getWriter().println("sapato Salvo");
    }
    // Read  /produtos
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<sapato> sapatos = this.sapatoRepositorio.ler();
        response.setContentType("text/html");
        System.out.println(sapatos);
        response.getWriter().println(sapatos.stream().map(sapato::toString).collect(Collectors.toList()));

    }
    // Update   /produtos/:id
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = getId(request);
        String nome = request.getParameter("nome");
        String preco = request.getParameter("preco");
        String imagem = request.getParameter("imagem");
        String tamanho = request.getParameter("tamanho");
        String marca = request.getParameter("marca");
        sapato p = sapato.builder()
                .nome(nome)
                .preco(Double.valueOf(preco))
                .imagemUrl(imagem)
                .tamanho(Integer.valueOf(tamanho))
                .marca(marca)
                .build();
        this.sapatoRepositorio.atualiza(p, id);
        response.setContentType("text/html");
        response.getWriter().println("sapato Atualizado");

    }
    // Delete  /produtos/:id
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = getId(request);
        this.sapatoRepositorio.deleta(id);
        response.setContentType("text/html");
        response.getWriter().println("sapato Deletado");
    }

    private static String getId(HttpServletRequest req){
        String path = req.getPathInfo();
        String[] paths = path.split("/");
        String id = paths[paths.length - 1];
        return id;
    }

}
