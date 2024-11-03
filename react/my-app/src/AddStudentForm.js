// addStudentForm.js
import React, { useState, useEffect } from "react";
import axios from "axios";

const AddStudentForm = ({
  onStudentAdded,
  onStudentUpdated,
  onDelete,
  student,
  isEditMode = false,
}) => {
  const [id, setId] = useState("");
  const [name, setName] = useState("");
  const [age, setAge] = useState("");
  const [courseNumber, setCourseNumber] = useState("");
  const [groupName, setGroupName] = useState("");
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    if (isEditMode && student) {
      setId(student.id);
      setName(student.name);
      setAge(student.age);
      setCourseNumber(student.courseNumber);
      setGroupName(student.groupName);
    } else {
      setId("");
      setName("");
      setAge("");
      setCourseNumber("");
      setGroupName("");
    }
  }, [student, isEditMode]);

  const handleSubmit = async (event) => {
    event.preventDefault();
    setLoading(true);

    const studentData = {
      id: parseInt(id, 10),
      name,
      age: parseInt(age, 10),
      courseNumber: parseInt(courseNumber, 10),
      groupName,
    };

    try {
      if (isEditMode) {
        await axios.put(`http://localhost:8080/students/${id}`, studentData);
        onStudentUpdated();
      } else {
        await axios.post("http://localhost:8080/students", studentData);
        onStudentAdded();
      }
    } catch (error) {
      console.error("Ошибка при отправке данных:", error);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="popup-content">
      <h2>{isEditMode ? "Редактировать студента" : "Добавить студента"}</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>ID:</label>
          <input
            type="number"
            value={id}
            onChange={(e) => setId(e.target.value)}
            required
            disabled={isEditMode}
          />
        </div>
        <div>
          <label>Имя:</label>
          <input
            type="text"
            value={name}
            onChange={(e) => setName(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Возраст:</label>
          <input
            type="number"
            value={age}
            onChange={(e) => setAge(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Номер курса:</label>
          <input
            type="number"
            value={courseNumber}
            onChange={(e) => setCourseNumber(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Название группы:</label>
          <input
            type="text"
            value={groupName}
            onChange={(e) => setGroupName(e.target.value)}
            required
          />
        </div>
        <button type="submit" disabled={loading}>
          {loading ? "Сохранение..." : "Сохранить изменения"}
        </button>
        {isEditMode && (
          <button
            type="button"
            onClick={onDelete}
            style={{ marginLeft: "10px", backgroundColor: "#d9534f" }}
          >
            Удалить
          </button>
        )}
      </form>
    </div>
  );
};

export default AddStudentForm;
