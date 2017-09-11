package ml.rhodes.libs.devrant;

import com.google.gson.JsonObject;

public class Comment extends RantContent {
    protected Comment(DevRant devRant, int id, User user, int score, int voteState, String content, Image image) {
        super(devRant, id, user, score, voteState, content, image);
    }

    static Comment fromJson(DevRant devRant, JsonObject json) {
        return new Comment(
                devRant,
                json.get("id").getAsInt(),
                User.fromJson(devRant, json),
                json.get("score").getAsInt(),
                json.get("vote_state").getAsInt(),
                json.get("body").getAsString(),
                Image.fromJson(json.get("attached_image"))
        );
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) && obj instanceof Comment;
    }
}
