package br.com.fiap.aplicacao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.fiap.dao.Dao;
import br.com.fiap.model.entity.Aluno;
import br.com.fiap.model.entity.Curso;
import br.com.fiap.model.entity.Disciplina;
import br.com.fiap.model.entity.Escola;
import br.com.fiap.model.entity.Nota;
import br.com.fiap.model.entity.Professor;
import br.com.fiap.model.entity.Usuario;
import br.com.fiap.model.enums.TipoNota;
import br.com.fiap.model.enums.TipoUsuario;

public class Aplicacao {
	public static void main(String[] args) {
/*		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("cadastroJPA");

		EntityManager em = emf.createEntityManager();
		
		// --- ESCOLA
		Dao<Escola> daoEscola = new Dao<Escola>(Escola.class, em);
		em.getTransaction().begin();
		Escola escola = new Escola();
		escola.setNome("Uniban");
		daoEscola.adiciona(escola);
		em.getTransaction().commit();
		
		// --- CURSO
		Dao<Curso> daoCurso = new Dao<Curso>(Curso.class, em);
		em.getTransaction().begin();
		Curso curso = new Curso();
		curso.setNome("Entity e JPA");
		curso.setEscola(escola);
		daoCurso.adiciona(curso);
		em.getTransaction().commit();

		// --- PROFESSOR
		Dao<Professor> daoProfessor = new Dao<Professor>(Professor.class, em);
		em.getTransaction().begin();
		Professor professor = new Professor();
		professor.setNome("Felipe");
		professor.setEscola(escola);
		daoProfessor.adiciona(professor);
		em.getTransaction().commit();
		
		// --- DISCIPLINA
		Dao<Disciplina> daoDisciplina = new Dao<Disciplina>(Disciplina.class,em);
		em.getTransaction().begin();
		Disciplina disciplina = new Disciplina();
		disciplina.setCurso(curso);
		disciplina.setNome("Apredendo JSF");
		disciplina.setProfessor(professor);
		daoDisciplina.adiciona(disciplina);
		em.getTransaction().commit();

		// --- ALUNO
		Dao<Aluno> daoAluno = new Dao<Aluno>(Aluno.class, em);
		em.getTransaction().begin();
		Aluno aluno = new Aluno();
		aluno.setCurso(curso);
		aluno.setNome("Edileno");
		daoAluno.adiciona(aluno);
		em.getTransaction().commit();

		// --- NOTA
		Dao<Nota> daoNota = new Dao<Nota>(Nota.class, em);
		em.getTransaction().begin();
		Nota nota = new Nota();
		nota.setAluno(aluno);
		nota.setDisciplina(disciplina);
		nota.setTipo(TipoNota.ATIVIDADE_PRATICA);
		nota.setValor(new Double(8));
		daoNota.adiciona(nota);
		em.getTransaction().commit();

		// --- USUARIO - ALUNO
		Dao<Usuario> daoUsuario = new Dao<Usuario>(Usuario.class, em);
		em.getTransaction().begin();
		Usuario usuario = new Usuario();
		usuario.setAluno(aluno);
		usuario.setNome("edileno");
		usuario.setSenha("123");
		usuario.setTipo(TipoUsuario.ALUNO);
		daoUsuario.adiciona(usuario);
		em.getTransaction().commit();

		// --- USUARIO - PROFESSOR
		em.getTransaction().begin();
		Usuario usuarioP = new Usuario();
		usuarioP.setProfessor(professor);
		usuarioP.setNome("felipe");
		usuarioP.setSenha("789");
		usuarioP.setTipo(TipoUsuario.PROFESSOR);
		daoUsuario.adiciona(usuarioP);
		em.getTransaction().commit();

		emf.close();
*/	}

}
