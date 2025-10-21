
package com.otd.otd_msa_back_life.community.entity;

import com.otd.otd_msa_back_life.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "post")
@Entity
@Table(
        name = "community_post_file",
        indexes = {
                @Index(name = "idx_postfile_post",  columnList = "post_id"),
                @Index(name = "idx_postfile_order", columnList = "post_id, order_idx")
        }
)
public class CommunityPostFile extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 게시글 FK */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "post_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_postfile_post")
    )
    private CommunityPost post;

    /** 업로더(작성자) ID */
    @Column(name = "user_id", nullable = false)
    private Long userId;

    /** 원본 파일명 */
    @Column(name = "file_name", length = 255, nullable = false)
    private String fileName;

    /** 브라우저에서 접근 가능한 저장 경로(예: /static/community/xxxx.jpg) */
    @Column(name = "file_path", length = 500, nullable = false)
    private String filePath;

    /** MIME 타입 (image/jpeg 등) */
    @Column(name = "file_type", length = 100)
    private String fileType;

    /** 파일 크기(byte) - 선택 */
    @Column(name = "file_size")
    private Long fileSize;

    /** 이미지 가로/세로 - 선택 */
    @Column(name = "width")
    private Integer width;

    @Column(name = "height")
    private Integer height;

    /** 정렬 순서(같은 post 내에서) */
    @Column(name = "order_idx", nullable = false)
    private Integer orderIdx;

    /** null로 들어오면 0으로 기본값 보정 */
    @PrePersist
    public void prePersist() {
        if (orderIdx == null) orderIdx = 0;
    }
}
