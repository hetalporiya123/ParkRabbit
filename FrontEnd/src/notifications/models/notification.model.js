// notification.model.js
export const createNotification = ({
  id,
  userId,
  type,
  message,
  read,
  createdAt,
}) => ({
  id,                 // backend ID (mandatory)
  userId,
  type,               // SESSION_STARTED | SESSION_REMINDER | SESSION_ENDED
  message,
  read: Boolean(read),
  createdAt,           // keep raw backend value
});
