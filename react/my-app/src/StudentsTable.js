import React, { useState, useEffect } from "react";
import axios from "axios";
import AddStudentForm from "./AddStudentForm";

const StudentsTable = () => {
  const [students, setStudents] = useState([]);
  const [loading, setLoading] = useState(false);
  const [isEditFormVisible, setIsEditFormVisible] = useState(false);
  const [editingStudent, setEditingStudent] = useState(null);
  const [showPopup, setShowPopup] = useState(false);
  const [isAddingStudent, setIsAddingStudent] = useState(false);

  const fetchStudents = async () => {
    setLoading(true);
    try {
      const response = await axios.get("http://localhost:8080/students");
      setStudents(response.data);
    } catch (error) {
      console.error("Ошибка при получении списка студентов:", error);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchStudents();
  }, []);

  const handleRowClick = (student) => {
    setEditingStudent(student);
    setIsEditFormVisible(true);
    setShowPopup(true);
  };

  const openAddStudentPopup = () => {
    setEditingStudent(null); // Сброс редактируемого студента
    setIsAddingStudent(true);
    setShowPopup(true);
  };

  const closePopup = () => {
    setShowPopup(false);
    setEditingStudent(null);
    setIsEditFormVisible(false);
    setIsAddingStudent(false); // Сброс состояния добавления
  };

  const handleDeleteStudent = async (id) => {
    if (window.confirm("Вы уверены, что хотите удалить этого студента?")) {
      try {
        await axios.delete(`http://localhost:8080/students/${id}`);
        fetchStudents();
      } catch (error) {
        console.error("Ошибка при удалении студента:", error);
      }
    }
  };

  return (
    <div
      style={{ display: "flex", justifyContent: "center", padding: "0 10%" }}
    >
      <div style={{ flex: 1, padding: "20px" }}>
        <h1>Список студентов</h1>
        <div style={{ marginBottom: "10px" }}>
          <button onClick={fetchStudents} disabled={loading}>
            {loading ? "Обновление..." : "Обновить"}
          </button>
          <button onClick={openAddStudentPopup} style={{ marginLeft: "10px" }}>
            Добавить студента
          </button>
        </div>

        <table>
          <thead>
            <tr>
              <th>ID</th>
              <th>Имя</th>
              <th>Возраст</th>
              <th>Номер курса</th>
              <th>Название группы</th>
            </tr>
          </thead>
          <tbody>
            {students.map((student) => (
              <tr
                key={student.id}
                onClick={() => handleRowClick(student)}
                className="student-row"
              >
                <td>{student.id}</td>
                <td>{student.name}</td>
                <td>{student.age}</td>
                <td>{student.courseNumber}</td>
                <td>{student.groupName}</td>
              </tr>
            ))}
          </tbody>
        </table>

        {showPopup && (
          <div
            style={{
              position: "fixed",
              top: 0,
              left: 0,
              right: 0,
              bottom: 0,
              backgroundColor: "rgba(0, 0, 0, 0.5)",
              display: "flex",
              justifyContent: "center",
              alignItems: "center",
              zIndex: 1000,
            }}
            onClick={closePopup}
          >
            <div
              onClick={(e) => e.stopPropagation()}
              style={{ position: "relative" }}
            >
              <AddStudentForm
                onStudentAdded={fetchStudents}
                onStudentUpdated={fetchStudents}
                student={editingStudent}
                isEditMode={isAddingStudent ? false : true}
                onDelete={() => handleDeleteStudent(editingStudent.id)}
              />
              <button
                onClick={closePopup}
                style={{
                  position: "absolute",
                  top: 5,
                  right: 5,
                  backgroundColor: "transparent",
                  border: "none",
                  cursor: "pointer",
                  fontSize: "20px",
                  color: "gray",
                }}
              >
                &times; {/* Крестик */}
              </button>
            </div>
          </div>
        )}
      </div>
    </div>
  );
};

export default StudentsTable;
