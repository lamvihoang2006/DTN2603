package backend;

public interface IQLPosition {
    void showAllPositions();
    void addPosition(String positionName);
    void deletePosition(int id);
    void updatePositionName(int id, String newName);
}