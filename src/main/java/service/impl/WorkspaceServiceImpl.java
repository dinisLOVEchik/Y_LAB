package service.impl;

import service.Workspace;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class WorkspaceServiceImpl {
    private List<Workspace> workspaces = new ArrayList<>();

    public String addWorkspace(String name, int capacity) {
        for (Workspace workspace : workspaces) {
            if (workspace.getName().equals(name)) {
                return "Совместно используемое имя рабочего пространства!";
            }
        }
        Workspace newWorkspace = new Workspace(name, capacity);
        workspaces.add(newWorkspace);
        return "Совместно используемое имя рабочего пространства!";
    }

    public Workspace getWorkspace(String name) {
        Optional<Workspace> workspace = workspaces.stream().filter(w -> w.getName().equals(name)).findFirst();
        if (workspace.isPresent()) {
            return workspace.get();
        }
        throw new NoSuchElementException("Workspace " + name + " not found");
    }

    public String deleteWorkspace(String name) {
        Optional<Workspace> workspace = workspaces.stream().filter(w -> w.getName().equals(name)).findFirst();
        if (workspace.isPresent()) {
            workspaces.remove(workspace.get());
            return "Workspace " + name + " deleted!";
        }
        throw new NoSuchElementException("Workspace " + name + " not found");
    }

    public List<Workspace> getWorkspaces() {
        return workspaces;
    }
}
