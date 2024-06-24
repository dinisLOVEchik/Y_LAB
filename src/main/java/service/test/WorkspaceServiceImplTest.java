package service.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import service.Workspace;
import service.impl.WorkspaceServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WorkspaceServiceImplTest {
    private WorkspaceServiceImpl workspaceService;
    private List<Workspace> mockWorkspaces;

    @BeforeEach
    public void setUp() {
        mockWorkspaces = new ArrayList<>();
        Workspace workspace1 = new Workspace("Test1", 10);
        Workspace workspace2 = new Workspace("Test2", 20);
        mockWorkspaces.add(workspace1);
        mockWorkspaces.add(workspace2);

        workspaceService = new WorkspaceServiceImpl();
        Mockito.when(workspaceService.getWorkspaces()).thenReturn(mockWorkspaces);
    }

    @Test
    public void testAddWorkspace() {
        String result = workspaceService.addWorkspace("Test3", 30);
        assertEquals("Совместно используемое имя рабочего пространства!", result);
    }

    @Test
    public void testGetWorkspace() {
        Workspace result = workspaceService.getWorkspace("Test1");
        assertEquals("Test1", result.getName());
        assertEquals(10, result.getCapacity());
    }

    @Test
    public void testDeleteWorkspace() {
        String result = workspaceService.deleteWorkspace("Test2");
        assertEquals("Workspace Test2 deleted!", result);
    }

    @Test
    public void testGetWorkspaces() {
        List<Workspace> result = workspaceService.getWorkspaces();
        assertEquals(2, result.size());
    }
}
