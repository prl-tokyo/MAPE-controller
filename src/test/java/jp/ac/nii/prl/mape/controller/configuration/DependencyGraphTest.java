package jp.ac.nii.prl.mape.controller.configuration;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import jp.ac.nii.prl.mape.controller.model.AP;

public class DependencyGraphTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testEmptyList() {
		DependencyGraph graph = new DependencyGraph();
		Iterator<AP> iter = graph.getIterator();
		assertFalse(iter.hasNext());
	}
	
	@Test
	public void testOneElement() {
		DependencyGraph graph = new DependencyGraph();
		AP ap = new AP();
		ap.setId(1L);
		ap.setName("ap1");
		ap.setPredecessors(new ArrayList<String>());
		graph.insert(ap);
		
		Iterator<AP> iter = graph.getIterator();
		assertTrue(iter.hasNext());
		assertTrue(iter.next().equals(ap));
		assertFalse(iter.hasNext());
	}
	
	@Test
	public void testThreeElementsNoDependency() {
		DependencyGraph graph = new DependencyGraph();
		
		AP ap1 = new AP();
		ap1.setId(1L);
		ap1.setName("ap1");
		ap1.setPredecessors(new ArrayList<String>());
		graph.insert(ap1);
		
		AP ap2 = new AP();
		ap2.setId(2L);
		ap2.setName("ap2");
		ap2.setPredecessors(new ArrayList<String>());
		graph.insert(ap2);
		
		AP ap3 = new AP();
		ap3.setId(3L);
		ap3.setName("ap3");
		ap3.setPredecessors(new ArrayList<String>());
		graph.insert(ap3);
		
		Iterator<AP> iter = graph.getIterator();
		iter.next();
		iter.next();
		iter.next();
		assertFalse(iter.hasNext());
	}
	
	@Test
	public void testElementsWithDependency() {
		DependencyGraph graph = new DependencyGraph();
		
		AP ap1 = new AP();
		ap1.setId(1L);
		ap1.setName("ap1");
		ap1.setPredecessors(new ArrayList<String>());
		graph.insert(ap1);
		
		AP ap2 = new AP();
		ap2.setId(2L);
		ap2.setName("ap2");
		List<String> predecessors = new ArrayList<String>();
		predecessors.add("ap1");
		ap2.setPredecessors(predecessors);
		graph.insert(ap2);
		
		Iterator<AP> iter = graph.getIterator();
		assertEquals("ap1", iter.next().getName());
		assertEquals("ap2", iter.next().getName());
		assertFalse(iter.hasNext());
	}
	
	@Test
	public void testElementsWithDependencyOtherInsertionOrder() {
		DependencyGraph graph = new DependencyGraph();
		
		AP ap2 = new AP();
		ap2.setId(2L);
		ap2.setName("ap2");
		List<String> predecessors = new ArrayList<String>();
		predecessors.add("ap1");
		ap2.setPredecessors(predecessors);
		graph.insert(ap2);

		AP ap1 = new AP();
		ap1.setId(1L);
		ap1.setName("ap1");
		ap1.setPredecessors(new ArrayList<String>());
		graph.insert(ap1);
		
		Iterator<AP> iter = graph.getIterator();
		assertEquals("ap1", iter.next().getName());
		assertEquals("ap2", iter.next().getName());
		assertFalse(iter.hasNext());
	}
}
