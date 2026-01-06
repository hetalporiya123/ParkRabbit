import { useNotifications } from "./NotificationContext";
import { formatCreatedAt } from "../services/dateTime.service";
export default function NotificationList() {
  const { notifications, markAsRead } = useNotifications();

  return (
    <div className="w-80 bg-white shadow-xl rounded-lg p-3">
      {notifications.length === 0 && (
        <p className="text-sm text-gray-500">No notifications</p>
      )}

      {notifications.map((n) => (
        <div
          key={n.id}
          className={`p-2 rounded mb-2 ${
            n.read ? "bg-gray-100" : "bg-blue-50 cursor-pointer"
          }`}
          onClick={() => !n.read && markAsRead(n.id)}
        >
          <div className="font-medium">{n.type}</div>
          <div className="text-sm text-gray-600">{n.message}</div>
          <div className="text-xs text-gray-400 mt-1">
            {formatCreatedAt(n.createdAt)}
          </div>
        </div>
      ))}
    </div>
  );
}
