/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author karina
 */

import Model.User.AuthService;
import Model.User.DAOUser;
import Model.User.ModelUser;
import Model.User.UserAuthService;
import java.util.Arrays;
import java.util.Optional;

public class ControllerLogin {

        private final LoginViewContract view;
        private final AuthService authService;

        public ControllerLogin() {
                this(null, new UserAuthService(new DAOUser()));
        }

        public ControllerLogin(LoginViewContract view) {
                this(view, new UserAuthService(new DAOUser()));
        }

        public ControllerLogin(LoginViewContract view, AuthService authService) {
                this.view = view;
                this.authService = authService;
        }

        public ModelUser login(
                        String username,
                        String password) {
                Optional<ModelUser> user = authService.authenticate(username, password);
                return user.orElse(null);
        }

        public void handleLogin(String username, char[] passwordChars) {
                if (view == null) {
                        return;
                }

                String safeUsername = username == null ? "" : username.trim();
                String password = new String(passwordChars);

                try {
                        if (safeUsername.isEmpty() || password.isEmpty()) {
                                view.showErrorMessage("Username dan password wajib diisi");
                                return;
                        }

                        Optional<ModelUser> userOpt = authService.authenticate(safeUsername, password);

                        if (userOpt.isEmpty()) {
                                view.showErrorMessage("Username / Password salah");
                                return;
                        }

                        view.showInfoMessage("Login berhasil");

                        if ("admin".equalsIgnoreCase(userOpt.get().getRole())) {
                                view.openAdminDashboard();
                        } else {
                                view.openUserDashboard();
                        }

                } finally {
                        Arrays.fill(passwordChars, '\0');
                }
        }
}