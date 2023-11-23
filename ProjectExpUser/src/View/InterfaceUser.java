package View;

import Controller.CTRLUser;
import Controller.CTRLcandidate;
import Controller.CTRLgraphic;
import Model.UserDAO;
import javax.swing.JButton;
import javax.swing.JTable;


public class InterfaceUser extends javax.swing.JFrame {

    UserDAO UD =new UserDAO();
    CTRLUser CU =new CTRLUser();
    CTRLcandidate ctrlCandidates = new CTRLcandidate();
    CTRLgraphic CTRLgraphic = new CTRLgraphic();

    public InterfaceUser() {
        this.setUndecorated(true);
        initComponents();
        setLocationRelativeTo(null);
        this.ctrlCandidates.loadCandidates(cbxCandidates);
        
    }
     public JButton btnVote() {
        return btnVoto;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        tbOrigin = new javax.swing.JTabbedPane();
        lblImageVote = new javax.swing.JPanel();
        srpCandidates = new javax.swing.JScrollPane();
        lblPhotoVote = new javax.swing.JLabel();
        cbxCandidates = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        btnVoto = new javax.swing.JButton();
        lblNameCandidateView = new javax.swing.JLabel();
        plResult = new javax.swing.JPanel();
        plFinalResult = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        jLabel2.setText("jLabel2");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Votaciones Nacionales");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(0, 204, 51));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 0, 0), 1, true));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Image/icons8-salida-50.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 700, 40, 40));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Resultados");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 130, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Votar");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 80, -1));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Image/papers.co-vm29-poly-art-abstract-blue-green-pattern-41-iphone-wallpaper.jpg"))); // NOI18N
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(-9, 1, 200, 740));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 0, 190, 740));

        tbOrigin.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        lblImageVote.setBackground(new java.awt.Color(255, 255, 255));
        lblImageVote.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 51, 0)));

        srpCandidates.setViewportView(lblPhotoVote);

        cbxCandidates.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxCandidatesItemStateChanged(evt);
            }
        });
        cbxCandidates.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbxCandidatesMouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        jLabel6.setText("Candidatos");

        btnVoto.setBackground(new java.awt.Color(0, 204, 0));
        btnVoto.setForeground(new java.awt.Color(255, 255, 255));
        btnVoto.setText("Votar");
        btnVoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnVotoMouseClicked(evt);
            }
        });
        btnVoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVotoActionPerformed(evt);
            }
        });

        lblNameCandidateView.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        javax.swing.GroupLayout lblImageVoteLayout = new javax.swing.GroupLayout(lblImageVote);
        lblImageVote.setLayout(lblImageVoteLayout);
        lblImageVoteLayout.setHorizontalGroup(
            lblImageVoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lblImageVoteLayout.createSequentialGroup()
                .addContainerGap(64, Short.MAX_VALUE)
                .addComponent(srpCandidates, javax.swing.GroupLayout.PREFERRED_SIZE, 757, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(lblImageVoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lblImageVoteLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addGroup(lblImageVoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnVoto)
                            .addComponent(cbxCandidates, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(40, Short.MAX_VALUE))
                    .addGroup(lblImageVoteLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(lblImageVoteLayout.createSequentialGroup()
                .addGap(340, 340, 340)
                .addComponent(lblNameCandidateView, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        lblImageVoteLayout.setVerticalGroup(
            lblImageVoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lblImageVoteLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(lblImageVoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(srpCandidates, javax.swing.GroupLayout.PREFERRED_SIZE, 585, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(lblImageVoteLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbxCandidates, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVoto, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNameCandidateView, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        tbOrigin.addTab("0", lblImageVote);

        plResult.setBackground(new java.awt.Color(255, 255, 255));
        plResult.setForeground(new java.awt.Color(255, 255, 255));
        plResult.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout plFinalResultLayout = new javax.swing.GroupLayout(plFinalResult);
        plFinalResult.setLayout(plFinalResultLayout);
        plFinalResultLayout.setHorizontalGroup(
            plFinalResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1158, Short.MAX_VALUE)
        );
        plFinalResultLayout.setVerticalGroup(
            plFinalResultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 494, Short.MAX_VALUE)
        );

        plResult.add(plFinalResult, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 6, -1, -1));

        jButton1.setBackground(new java.awt.Color(0, 102, 0));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Graficar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        plResult.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 600, 257, -1));

        tbOrigin.addTab("1", plResult);

        getContentPane().add(tbOrigin, new org.netbeans.lib.awtextra.AbsoluteConstraints(152, 0, 1220, 740));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVotoActionPerformed
        // TODO add your handling code here:
        this.ctrlCandidates.addVote(cbxCandidates.getSelectedItem().toString());
        
    /* Obtén el ID del usuario (puedes obtenerlo de alguna manera, dependiendo de tu aplicación)
    int userId = obtenerIdUsuario();

    // Actualiza el estado del usuario (cambia 1 por el nuevo estado que desees asignar)
    UD.updateUserState(userId, 1);*/
        
    }//GEN-LAST:event_btnVotoActionPerformed

    private void cbxCandidatesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbxCandidatesMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cbxCandidatesMouseClicked

    private void cbxCandidatesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxCandidatesItemStateChanged
        // TODO add your handling code here:
        this.ctrlCandidates.setImageInLabel(this.ctrlCandidates.PhotoVoteCandidate(cbxCandidates.getSelectedItem().toString()), lblPhotoVote);
        lblNameCandidateView.setText(cbxCandidates.getSelectedItem().toString());
    }//GEN-LAST:event_cbxCandidatesItemStateChanged

    private void btnVotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVotoMouseClicked
        // TODO add your handling code here:
        btnVoto.setVisible(false);
        
    }//GEN-LAST:event_btnVotoMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.CTRLgraphic.addGraph(plFinalResult, this.CTRLgraphic.addnSources1Reports2());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:
        tbOrigin.setSelectedIndex(1);
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        // TODO add your handling code here:
        tbOrigin.setSelectedIndex(0);
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
           dispose();
        Login lg  = new Login();
        lg.setVisible(true);
    }//GEN-LAST:event_jLabel3MouseClicked

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVoto;
    private javax.swing.JComboBox<String> cbxCandidates;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel lblImageVote;
    private javax.swing.JLabel lblNameCandidateView;
    private javax.swing.JLabel lblPhotoVote;
    private javax.swing.JPanel plFinalResult;
    private javax.swing.JPanel plResult;
    private javax.swing.JScrollPane srpCandidates;
    private javax.swing.JTabbedPane tbOrigin;
    // End of variables declaration//GEN-END:variables
}
