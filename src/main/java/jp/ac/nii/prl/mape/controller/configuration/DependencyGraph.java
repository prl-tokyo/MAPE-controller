package jp.ac.nii.prl.mape.controller.configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import jp.ac.nii.prl.mape.controller.model.AP;

public class DependencyGraph {

	private Map<String, List<String>> adj;

	private Map<String, AP> aps;
	
	@Autowired
	public DependencyGraph() {
		adj = new HashMap<>();
		aps = new HashMap<>();
		adj.put("root", new ArrayList<String>());
	}
	
	public void insert(AP ap) {
		aps.put(ap.getName(), ap);
		insert(ap.getName(), ap.getPredecessors());
	}
	
	public Iterator<AP> getIterator() {
		return new DependencyIterator<AP>(aps, adj);
	}
	
	private class DependencyIterator<T> implements Iterator<AP> {

		private List<AP> aps;
		
		private DependencyIterator(Map<String, AP> aps, Map<String, List<String>> adj) {
			this.aps = sort(aps, adj);
		}
		
		@Override
		public boolean hasNext() {
			return !aps.isEmpty();
		}

		@Override
		public AP next() {
			return aps.remove(0);
		}
		
		private List<AP> sort(Map<String, AP> aps, Map<String, List<String>> adj) {
			List<AP> sorted = new ArrayList<AP>();
			List<String> queue = new ArrayList<String>();
			queue.add("root");
			while (!queue.isEmpty()) {
				String current = queue.remove(0);
				if (!current.equals("root"))
					sorted.add(aps.get(current));
				List<String> succs = adj.get(current);
				queue.addAll(succs);
			}
			return sorted;
		}
		
	}
	
	private void insert(String node, Collection<String> predecessors) {
		if (predecessors.isEmpty())
			add(node, "root");
		else
			for (String predecessor:predecessors)
				add(node, predecessor);
	 }
	
	private void add(String node, String predecessor) {
		if (adj.containsKey(predecessor)) {
			adj.get(predecessor).add(node);
		} else {
			List<String> succ = new ArrayList<String>();
			succ.add(node);
			adj.put(predecessor, succ);
		}
		if (adj.get(node) == null)
			adj.put(node, new ArrayList<String>());
	}
}
