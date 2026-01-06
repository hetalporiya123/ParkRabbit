// auth.service.js
import { useNotifications } from "../notifications/useNotifications";
export const logout = () => {
  const {clearNotifications} = useNotifications();
  clearNotifications();
  localStorage.removeItem("token");

};
