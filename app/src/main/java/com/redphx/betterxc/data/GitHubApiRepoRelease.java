package com.redphx.betterxc.data;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GitHubApi.kt */
@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\b\u0087\b\u0018\u00002\u00020\u0001B+\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\u000e\b\u0001\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0004\b\b\u0010\tJ\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0003¢\u0006\u0002\u0010\u000eJ2\u0010\u0018\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00032\u000e\b\u0003\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0001¢\u0006\u0002\u0010\u0019J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0019\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000e¨\u0006\u001b"}, d2 = {"Lcom/redphx/betterxc/data/GitHubApiRepoRelease;", "", "tagName", "", "name", "assets", "", "Lcom/redphx/betterxc/data/GitHubApiRepoReleaseAsset;", "<init>", "(Ljava/lang/String;Ljava/lang/String;[Lcom/redphx/betterxc/data/GitHubApiRepoReleaseAsset;)V", "getTagName", "()Ljava/lang/String;", "getName", "getAssets", "()[Lcom/redphx/betterxc/data/GitHubApiRepoReleaseAsset;", "[Lcom/redphx/betterxc/data/GitHubApiRepoReleaseAsset;", "equals", "", "other", "hashCode", "", "component1", "component2", "component3", "copy", "(Ljava/lang/String;Ljava/lang/String;[Lcom/redphx/betterxc/data/GitHubApiRepoReleaseAsset;)Lcom/redphx/betterxc/data/GitHubApiRepoRelease;", "toString", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final /* data */ class GitHubApiRepoRelease {
    private final GitHubApiRepoReleaseAsset[] assets;
    private final String name;
    private final String tagName;

    public static /* synthetic */ GitHubApiRepoRelease copy$default(GitHubApiRepoRelease gitHubApiRepoRelease, String str, String str2, GitHubApiRepoReleaseAsset[] gitHubApiRepoReleaseAssetArr, int i, Object obj) {
        if ((i & 1) != 0) {
            str = gitHubApiRepoRelease.tagName;
        }
        if ((i & 2) != 0) {
            str2 = gitHubApiRepoRelease.name;
        }
        if ((i & 4) != 0) {
            gitHubApiRepoReleaseAssetArr = gitHubApiRepoRelease.assets;
        }
        return gitHubApiRepoRelease.copy(str, str2, gitHubApiRepoReleaseAssetArr);
    }

    /* renamed from: component1, reason: from getter */
    public final String getTagName() {
        return this.tagName;
    }

    /* renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component3, reason: from getter */
    public final GitHubApiRepoReleaseAsset[] getAssets() {
        return this.assets;
    }

    public final GitHubApiRepoRelease copy(@Json(name = "tag_name") String tagName, @Json(name = "name") String name, @Json(name = "assets") GitHubApiRepoReleaseAsset[] assets) {
        Intrinsics.checkNotNullParameter(tagName, "tagName");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(assets, "assets");
        return new GitHubApiRepoRelease(tagName, name, assets);
    }

    public String toString() {
        return "GitHubApiRepoRelease(tagName=" + this.tagName + ", name=" + this.name + ", assets=" + Arrays.toString(this.assets) + ')';
    }

    public GitHubApiRepoRelease(@Json(name = "tag_name") String tagName, @Json(name = "name") String name, @Json(name = "assets") GitHubApiRepoReleaseAsset[] assets) {
        Intrinsics.checkNotNullParameter(tagName, "tagName");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(assets, "assets");
        this.tagName = tagName;
        this.name = name;
        this.assets = assets;
    }

    public final String getTagName() {
        return this.tagName;
    }

    public final String getName() {
        return this.name;
    }

    public final GitHubApiRepoReleaseAsset[] getAssets() {
        return this.assets;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
            return false;
        }
        Intrinsics.checkNotNull(other, "null cannot be cast to non-null type com.redphx.betterxc.data.GitHubApiRepoRelease");
        GitHubApiRepoRelease gitHubApiRepoRelease = (GitHubApiRepoRelease) other;
        return Intrinsics.areEqual(this.tagName, gitHubApiRepoRelease.tagName) && Intrinsics.areEqual(this.name, gitHubApiRepoRelease.name) && Arrays.equals(this.assets, gitHubApiRepoRelease.assets);
    }

    public int hashCode() {
        return (((this.tagName.hashCode() * 31) + this.name.hashCode()) * 31) + Arrays.hashCode(this.assets);
    }
}
