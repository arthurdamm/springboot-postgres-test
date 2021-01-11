package com.arthurdamm.sbnotes;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IdeaRepo extends JpaRepository<Idea, Integer> {
	// @Automagically
}
