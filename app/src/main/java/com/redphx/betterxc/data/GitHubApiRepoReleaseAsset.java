package com.redphx.betterxc.data;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GitHubApi.kt */
@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/redphx/betterxc/data/GitHubApiRepoReleaseAsset;", "", "name", "", "downloadUrl", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "getDownloadUrl", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final /* data */ class GitHubApiRepoReleaseAsset {
    private final String downloadUrl;
    private final String name;

    public static /* synthetic */ GitHubApiRepoReleaseAsset copy$default(GitHubApiRepoReleaseAsset gitHubApiRepoReleaseAsset, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = gitHubApiRepoReleaseAsset.name;
        }
        if ((i & 2) != 0) {
            str2 = gitHubApiRepoReleaseAsset.downloadUrl;
        }
        return gitHubApiRepoReleaseAsset.copy(str, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component2, reason: from getter */
    public final String getDownloadUrl() {
        return this.downloadUrl;
    }

    public final GitHubApiRepoReleaseAsset copy(@Json(name = "name") String name, @Json(name = "browser_download_url") String downloadUrl) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(downloadUrl, "downloadUrl");
        return new GitHubApiRepoReleaseAsset(name, downloadUrl);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GitHubApiRepoReleaseAsset)) {
            return false;
        }
        GitHubApiRepoReleaseAsset gitHubApiRepoReleaseAsset = (GitHubApiRepoReleaseAsset) other;
        return Intrinsics.areEqual(this.name, gitHubApiRepoReleaseAsset.name) && Intrinsics.areEqual(this.downloadUrl, gitHubApiRepoReleaseAsset.downloadUrl);
    }

    public int hashCode() {
        return (this.name.hashCode() * 31) + this.downloadUrl.hashCode();
    }

    public String toString() {
        return "GitHubApiRepoReleaseAsset(name=" + this.name + ", downloadUrl=" + this.downloadUrl + ')';
    }

    public GitHubApiRepoReleaseAsset(@Json(name = "name") String name, @Json(name = "browser_download_url") String downloadUrl) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(downloadUrl, "downloadUrl");
        this.name = name;
        this.downloadUrl = downloadUrl;
    }

    public final String getName() {
        return this.name;
    }

    public final String getDownloadUrl() {
        return this.downloadUrl;
    }
}
