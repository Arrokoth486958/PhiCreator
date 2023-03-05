package dev.arrokoth.phicreator.chart.phi.chart.loader;

import dev.arrokoth.phicreator.chart.phi.chart.Chart;
import dev.arrokoth.phicreator.chart.phi.chart.objects.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Arrokoth
 * @project PhiCreator
 * @copyright Copyright © 2023 Arrokoth All Rights Reserved.
 */
public class JSONChartLoader extends ChartLoader {
    private final JSONObject json;
    private final URL illustration;
    private final URL music;

    public JSONChartLoader(JSONObject json, URL illustration, URL music) {
        this.json = json;
        this.illustration = illustration;
        this.music = music;
    }

    @Override
    public Chart load() {
        // Format version, currently is 3
        int formatVersion = json.getInt("formatVersion");

        // Total number of notes
        int numOfNotes = json.getInt("numOfNotes");

        // TODO: what is this?
        double offset = json.getDouble("offset");

        // The content of the chart
        JSONArray judgeLineList = json.getJSONArray("judgeLineList");

        List<JudgeLine> lines = new ArrayList<>();

        for (Object lineObj : judgeLineList) {
            if (lineObj instanceof JSONObject) {
                JSONObject lineJson = (JSONObject) lineObj;

                Map<Double, Note> notesAbove = new HashMap<>();
                for (Object noteObj : lineJson.getJSONArray("notesAbove")) {
                    if (noteObj instanceof JSONObject) {
                        JSONObject noteJson = (JSONObject) noteObj;

                        int type = noteJson.getInt("type");
                        double time = noteJson.getDouble("time");
                        double positionX = noteJson.getDouble("positionX");
                        double holdTime = noteJson.getDouble("holdTime");
                        double speed = noteJson.getDouble("speed");
                        double floorPosition = noteJson.getDouble("floorPosition");

                        notesAbove.put(time, new Note(type, time, positionX, holdTime, speed, floorPosition));
                    }
                }

                Map<Double, Note> notesBelow = new HashMap<>();
                for (Object noteObj : lineJson.getJSONArray("notesBelow")) {
                    if (noteObj instanceof JSONObject) {
                        JSONObject noteJson = (JSONObject) noteObj;

                        int type = noteJson.getInt("type");
                        double time = noteJson.getDouble("time");
                        double positionX = noteJson.getDouble("positionX");
                        double holdTime = noteJson.getDouble("holdTime");
                        double speed = noteJson.getDouble("speed");
                        double floorPosition = noteJson.getDouble("floorPosition");

                        notesBelow.put(time, new Note(type, time, positionX, holdTime, speed, floorPosition));
                    }
                }

                Map<Double, LineSpeedEvent> speedEvents = new HashMap<>();
                for (Object speedObject : lineJson.getJSONArray("speedEvents")) {
                    if (speedObject instanceof JSONObject) {
                        JSONObject speedJson = (JSONObject) speedObject;

                        double startTime = speedJson.getDouble("startTime");
                        double endTime = speedJson.getDouble("endTime");
                        double floorPosition = speedJson.getDouble("floorPosition");
                        double value = speedJson.getDouble("value");

                        speedEvents.put(startTime, new LineSpeedEvent(startTime, endTime, floorPosition, value));
                    }
                }

                Map<Double, LineMoveEvent> moveEvents = new HashMap<>();
                for (Object moveObject : lineJson.getJSONArray("judgeLineMoveEvents")) {
                    if (moveObject instanceof JSONObject) {
                        JSONObject moveJson = (JSONObject) moveObject;
                        moveEvents.put(moveJson.getDouble("startTime"), new LineMoveEvent(moveJson.getDouble("startTime"), moveJson.getDouble("endTime"), moveJson.getDouble("start"), moveJson.getDouble("end"), moveJson.getDouble("start2"), moveJson.getDouble("end2")));
                    }
                }

                Map<Double, LineRotateEvent> rotateEvents = new HashMap<>();
                for (Object rotateObject : lineJson.getJSONArray("judgeLineRotateEvents")) {
                    if (rotateObject instanceof JSONObject) {
                        JSONObject rotateJson = (JSONObject) rotateObject;
                        rotateEvents.put(rotateJson.getDouble("startTime"), new LineRotateEvent(rotateJson.getDouble("startTime"), rotateJson.getDouble("endTime"), rotateJson.getDouble("start"), rotateJson.getDouble("end"), rotateJson.getDouble("start2"), rotateJson.getDouble("end2")));
                    }
                }

                Map<Double, LineDisappearEvent> disappearEvent = new HashMap<>();
                for (Object disappearObject : lineJson.getJSONArray("judgeLineDisappearEvents")) {
                    if (disappearObject instanceof JSONObject) {
                        JSONObject disappearJson = (JSONObject) disappearObject;
                        disappearEvent.put(disappearJson.getDouble("startTime"), new LineDisappearEvent(disappearJson.getDouble("startTime"), disappearJson.getDouble("endTime"), disappearJson.getDouble("start"), disappearJson.getDouble("end"), disappearJson.getDouble("start2"), disappearJson.getDouble("end2")));
                    }
                }

                lines.add(new JudgeLine(lineJson.getInt("bpm"), lineJson.getInt("numOfNotesAbove"), lineJson.getInt("numOfNotesBelow"), notesAbove, notesBelow, speedEvents, rotateEvents, moveEvents, disappearEvent));
            }
        }
        return new Chart(formatVersion, numOfNotes, offset, lines, illustration, music);
    }
}
