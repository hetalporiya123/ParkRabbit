import GenericFormComponent from "./GenericFormComponent";
import {login} from '../api/auth'
import { useNavigate } from "react-router";
export default function LoginComponent() {
  const navigate = new useNavigate()
  const handleLogin = async (formData) => {
    try {
      const response = await login(formData);
      localStorage.setItem("token", response.token);
       navigate(`/dashboard`);
    } catch (error) {
      console.error("Login failed", error);
    }
  };

  const handleRegister = ()=> {
    navigate(`/signup`)
  }

  const formConfig = {
  meta: {
    title: "LogIn",
    subtitle: "If you already have an account in ParkRabbit simple login to ParkRabbit",
  },

  fields: [
    {
      name: "username",
      label: "Username",
      type: "text",
      required: true,
      placeholder: "Enter your username",
    },
    {
      name: "password",
      label: "Password",
      type: "password",
      required: true,
      placeholder: "••••••••",
    },
  ],

  actions: {
    submit: {
      show: true,
      text: "Login",
      variant: "contained",
      style: "primary",
      type: "submit",
      onSubmit: handleLogin
    },
    subActions: {
      show: true,
      text: "Register",
      variant: "contained",
      type: "submit",
      style: "primary cursor-pointer text-blue-600" ,
      onClick: handleRegister
    }
  },
};

  return (
    <GenericFormComponent config = {formConfig} />
  );
}
