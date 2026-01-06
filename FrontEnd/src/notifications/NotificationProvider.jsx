import { useState } from "react";
import { NotificationContext } from "./NotificationContext";

const normalizeCreatedAt = (createdAt) => {
  if (Array.isArray(createdAt)) {
    const [y, m, d, h, min, s] = createdAt;

    const iso = new Date(y, m - 1, d, h, min, s).toISOString();
    console.log("Normalized ISO:", iso);

    return iso;
  }

  // ISO string from backend
  if (typeof createdAt === "string") {
    return createdAt;
  }

  return null;
};

export default function NotificationProvider({ children }) {
  const [notifications, setNotifications] = useState([]);

  const addNotification = (notification) => {
    if (!notification?.id) return;

    setNotifications((prev) => {
      const exists = prev.some((n) => n.id === notification.id);
      if (exists) return prev;

      return [
        {
          id: notification.id,
          userId: notification.userId,
          type: notification.type,
          message: notification.message,
          read: notification.read ?? false,
          createdAt: normalizeCreatedAt(notification?.createdAt),
        },
        ...prev,
      ];
    });
  };

  const markAsRead = (id) => {
    setNotifications((prev) =>
      prev.map((n) => (n.id === id ? { ...n, read: true } : n))
    );
  };
  const clearNotifications = () => {
    setNotifications([]);
  };

  return (
    <NotificationContext.Provider
      value={{ notifications, addNotification, markAsRead, clearNotifications }}
    >
      {children}
    </NotificationContext.Provider>
  );
}
