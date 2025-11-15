package com.redphx.betterxc.data;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GitHubApi.kt */
@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Lcom/redphx/betterxc/data/GitHubApiRepoBranchCommit;", "", "sha", "", "<init>", "(Ljava/lang/String;)V", "getSha", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "com.redphx.betterxc_0.24.2-39_release"}, k = 1, mv = {2, 0, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final /* data */ class GitHubApiRepoBranchCommit {
    private final String sha;

    public static /* synthetic */ GitHubApiRepoBranchCommit copy$default(GitHubApiRepoBranchCommit gitHubApiRepoBranchCommit, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = gitHubApiRepoBranchCommit.sha;
        }
        return gitHubApiRepoBranchCommit.copy(str);
    }

    /* renamed from: component1, reason: from getter */
    public final String getSha() {
        return this.sha;
    }

    public final GitHubApiRepoBranchCommit copy(String sha) {
        Intrinsics.checkNotNullParameter(sha, "sha");
        return new GitHubApiRepoBranchCommit(sha);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof GitHubApiRepoBranchCommit) && Intrinsics.areEqual(this.sha, ((GitHubApiRepoBranchCommit) other).sha);
    }

    public int hashCode() {
        return this.sha.hashCode();
    }

    public String toString() {
        return "GitHubApiRepoBranchCommit(sha=" + this.sha + ')';
    }

    public GitHubApiRepoBranchCommit(String sha) {
        Intrinsics.checkNotNullParameter(sha, "sha");
        this.sha = sha;
    }

    public final String getSha() {
        return this.sha;
    }
}
