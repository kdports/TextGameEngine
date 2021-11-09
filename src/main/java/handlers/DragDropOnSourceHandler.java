// package handlers;
//
// import client.GUIDecision;
// import entities.Slide;
//
// import interfaces.EditDisplayer;
// import entities.Studio;
// import javafx.scene.input.DragEvent;
// import javafx.scene.input.Dragboard;
//
// public class DragDropOnSourceHandler extends BaseHandler<DragEvent> {
//     private int slideId;
//     private int decisionId;
//
//     public DragDropOnSourceHandler(EditDisplayer displayer, Studio studio, int idControl) {
//         super(displayer, studio, idControl);
//     }
//
//     public void setAttributes(int slideId, int decisionId) {
//         this.slideId = slideId;
//         this.decisionId = decisionId;
//     }
//
//     @Override
//     public void handle(DragEvent event) {
//         dragDropOnSourceHandler.setAttributes(idControl, )
//
//         Dragboard db = event.getDragboard();
//         if (db.hasString()) {
//             System.out.println("Dropped: " + db.getString());
//             StringBuilder desiredDecision = new StringBuilder(db.getString());
//             desiredDecision.deleteCharAt(0);
//             String outputDecision = desiredDecision.toString();
//             if (Character.toString(db.getString().charAt(0)).equals("0")){
//                 // GUIDecision initialDecision = (GUIDecision) root.lookup("#" + outputDecision);
//                 // initialDecision.initialScene = this;
//             } else {
//                 GUIDecision initialDecision = (GUIDecision) root.lookup("#" + outputDecision);
//                 initialDecision.setTargetSlide(this);
//                 this.studio.
//             }
//             event.setDropCompleted(true);
//         } else {
//             event.setDropCompleted(false);
//         }
//         event.consume();
//     }
//
//     public void handleDropOnSource(int decisionId, int slideId) {
//         Slide sourceSlide = this.studio.getSlide(slideId);
//         this.studio.addDecision(sourceSlide, decisionId)
//     }
// }
